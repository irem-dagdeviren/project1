import http from "@/lib/http.js";

export function signUp(body) {
    return http.post('/dev/v1/auth/register', body);
}