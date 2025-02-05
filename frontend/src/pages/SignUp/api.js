import axios from "axios";
import {i18nInstance} from "../../locales/index.js";


export function signUp(body) {
    return axios.post('/dev/v1/auth/register', body, {
        headers: {
            "Accept-Language": i18nInstance.language
        }
    });
}