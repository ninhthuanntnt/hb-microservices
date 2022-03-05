import React, {useCallback, useEffect, useState} from "react";
import "./index.less";
import {Button, Col, Form, Input, Row, Tag, Upload} from "antd";
import {CKEditor} from '@ckeditor/ckeditor5-react';
import CustomInlineEditor from '../../../../components/CustomInlineEditor/ckeditor';
import TextArea from "antd/es/input/TextArea";
import {DeleteOutlined, InboxOutlined} from '@ant-design/icons';
import {BASE_URL} from "../../../../const/constant";
import {UploadChangeParam} from "antd/lib/upload/interface";
import OverlapActions from "../../../../components/OverlapActions";
import CkUploadAdapter from "../../../../components/CkUploadAdapter";
import {WithContext as ReactTags} from 'react-tag-input';
import tagsApi from "../../../../api/tagsApi";
import postsApi from "../../../../api/postsApi";
import {PostCreateReq, PostType} from "../../../../models/request/PostCreateReq";
import {history} from "../../../../utils/history";
import {NotificationUtils} from "../../../../utils/NotificationUtils";
import {UriUtils} from "../../../../utils/UriUtils";
import {useParams} from "react-router-dom";
import {parseInt} from "lodash";
import axiosClient from "../../../../api/axiosClient";

const KeyCodes = {
    comma: 188,
    enter: 13
};

const delimiters = [KeyCodes.comma, KeyCodes.enter];

interface Tag {
    id: string,
    name: string,
}

interface PostDetailParam {
    id: string
}

const PostCreation: React.FC = () => {
    const postDetailParam = useParams<PostDetailParam>();
    const postId = parseInt(postDetailParam.id);

    const [form] = Form.useForm();
    const [tags, setTags] = useState<Tag[]>([]);
    const [tagSuggestions, setTagSuggestions] = useState<Tag[]>([]);
    const [lastSuggestion, setLastSuggestion] = useState<number>(new Date().getTime());
    const [coverImagePath, setCoverImagePath] = useState<string>();
    const [ckEditorContent, setCkEditorContent] = useState<string>("");

    // const getBase64 = (file) => {
    //     return new Promise<any>((resolve, reject) => {
    //         const reader = new FileReader();
    //         reader.readAsDataURL(file);
    //         reader.onload = () => resolve(reader.result);
    //         reader.onerror = error => reject(error);
    //     });
    // }

    useEffect(() => {
        if(postId) {
            postsApi.getPostDetailForCurrentUser(postId)
                .then(post => {
                    form.setFields([
                        {
                            name: "title",
                            value: post.title
                        },
                        {
                            name: "summary",
                            value: post.summary
                        }
                    ]);

                    setCkEditorContent(post.content);
                    setCoverImagePath(post.coverImagePath);
                    setTags(post?.tags?.map(value => {
                        return {
                            id: value.name,
                            name: value.name,
                        }
                    }));
                })
        }
    }, []);

    const onChangeCkEditor = useCallback((event, editor) => {
        form.setFieldsValue({content: editor.getData()});
    }, []);

    const onChangeCoverImage = useCallback((info: UploadChangeParam) => {
        console.log(info);
        switch (info.file.status) {
            case "error":
                console.log("ERROR: ", info);
                NotificationUtils.showErrorFromResponse(info.file.response);
                break;
            case "uploading":
                break;
            case "done":
                setCoverImagePath(info.file.response.path);
                break;
            case "success":
                break;
        }
    }, [coverImagePath]);

    const handleAdditionTag = useCallback(tag => {
        let finalTagName = tag.name.toLowerCase();
        if (/^[a-zA-Z0-9\-]+$/g.test(finalTagName))
            setTags([...tags, {
                id: finalTagName,
                name: finalTagName,
            }]);
        else
            return;
    }, [tags]);

    const handleDeleteTag = useCallback(i => {
        setTags(tags.filter((tag, index) => index !== i));
    }, [tags]);

    const handleTagInputChange = useCallback(value => {
        if (value && new Date().getTime() - lastSuggestion > 500) {
            tagsApi.searchTag(value)
                .then(basePagination => {
                    setTagSuggestions(basePagination.items.map(item => {
                        return {
                            id: item.name,
                            name: item.name,
                        }
                    }));
                });
            setLastSuggestion(new Date().getTime());
        }
    }, [lastSuggestion, tagSuggestions]);

    const onDeleteBackground = useCallback((e) => {
        setCoverImagePath(undefined);

        e.stopPropagation();
    }, [coverImagePath]);

    const onFinishCreatePost = useCallback((values) => {
        const postCreateReq: PostCreateReq = {
            title: values.title,
            summary: values.summary,
            content: values.content,
            categoryId: 1,
            postType: PostType.NORMAL,
            tags: tags.map((tag) => {
                return {
                    name: tag.name,
                };
            }),
            coverImagePath: coverImagePath,
        };

        if(postId) {
            postsApi
                .updatePost(postId, postCreateReq)
                .then(value => {
                    history.push(`/posts/${postId}/${values.title}`);
                });
        } else {
            postsApi
                .createNewPost(postCreateReq)
                .then(value => {
                    history.push(`/posts/${value}/${values.title}`);
                });
        }
    }, [tags, coverImagePath]);

    return (
        <Col md={19}>
            <Form form={form} onFinish={onFinishCreatePost}>
                <Form.Item name={"submit"}>
                    <Button htmlType={"submit"}>
                        Publish
                    </Button>
                </Form.Item>
                <Form.Item>
                    <Upload.Dragger name={"file"}
                                    action={`${BASE_URL}/hb-dmm/api/v1/user/files/images`}
                                    withCredentials={true}
                                    maxCount={1}
                                    showUploadList={false}
                                    onChange={onChangeCoverImage}>
                        {
                            coverImagePath ? (
                                <div className={"overlap-container avatar-uploader"} style={{position: "relative"}}>
                                    <OverlapActions actionItems={() => (
                                        <>
                                            <DeleteOutlined style={{color: "white", fontSize: 20}}
                                                            onClick={onDeleteBackground}/>
                                        </>
                                    )}/>
                                    <img style={{width: "100%"}} src={UriUtils.getImageUrlFromPath(coverImagePath)}/>
                                </div>
                            ) : (
                                <>
                                    <p className="ant-upload-drag-icon">
                                        <InboxOutlined/>
                                    </p>
                                    <p className="ant-upload-text">Click or drag image to this area to upload
                                        background</p>
                                </>
                            )
                        }
                    </Upload.Dragger>
                </Form.Item>
                <Form.Item name={"title"} rules={[{
                    required: true
                }]}>
                    <Input placeholder={"Title"}/>
                </Form.Item>
                <Form.Item>
                    <Input hidden value={tags.map(value => value.name).join(",")}/>
                    <Row className={"ReactTags"}>
                        <ReactTags tags={tags}
                                   delimiters={delimiters}
                                   suggestions={tagSuggestions}
                                   handleAddition={handleAdditionTag}
                                   handleInputChange={handleTagInputChange}
                                   handleDelete={handleDeleteTag}
                                   inputFieldPosition="bottom"
                                   placeholder={"New tag"}
                                   labelField="name"
                                   minQueryLength={1}
                                   autocomplete/>
                    </Row>
                </Form.Item>
                <Form.Item name={"summary"}>
                    <TextArea placeholder={"Summary"}/>
                </Form.Item>
                <Form.Item rules={[{
                    required: true
                }]} name={"content"}>
                    <Input hidden/>
                    <CKEditor editor={CustomInlineEditor}
                              onChange={onChangeCkEditor}
                              onReady={(editor) => {
                                  editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                                      return new CkUploadAdapter(loader);
                                  }
                              }}
                              data={ckEditorContent}
                              config={{
                                  placeholder: "Content"
                              }}/>
                </Form.Item>
            </Form>
        </Col>
    )
}

export default PostCreation;