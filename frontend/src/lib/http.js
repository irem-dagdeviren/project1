import axios from "axios";
import { i18nInstance} from "../locales/index.js";
import {loadToken, storeToken} from "@/shared/state/storage.js";

const http = axios.create();

let authToken = loadToken();
export function setToken(token) {
    authToken = token;
    storeToken(token)
}

const createAuthInterceptor = (getToken) => {
    return (config) => {
        config.headers["Accept-Language"] = i18nInstance.language;
        const token = getToken();
        if (token) {
            config.headers["Authorization"] = `${token.prefix} ${token.token}`;
        }
        return config;
    };
};

const authInterceptor = createAuthInterceptor(loadToken);
http.interceptors.request.use(authInterceptor);

export default http;