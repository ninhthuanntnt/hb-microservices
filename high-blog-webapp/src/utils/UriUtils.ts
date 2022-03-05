import {BASE_URL} from "../const/constant";

export class UriUtils {
    static getEncodeUrl(url: string) {
        return encodeURIComponent(url)
            .replaceAll(/[$&+,:;=?@#|'<>.^*()%!-]/g, "")
            .replaceAll(" ", "-");
    }

    static getImageUrlFromPath(path: string | undefined) {
        return `${BASE_URL}/hb-dmm/${path}`;
    }
}