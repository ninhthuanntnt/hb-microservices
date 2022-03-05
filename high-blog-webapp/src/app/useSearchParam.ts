import {useLocation} from "react-router-dom";

export default function useSearchParam(param: string): string {
    let search = new URLSearchParams(useLocation().search);

    return search.get(param) ?? "";
}