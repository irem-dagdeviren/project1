import http from "@/lib/http.js";


export function activateUser(token) {
    return http.put(`/dev/v1/auth/register/${token}/activate`);
}