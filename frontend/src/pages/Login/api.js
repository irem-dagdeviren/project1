import http from "@/lib/http";

export function login(credentials){
    return http.post("/dev/v1/auth/authorize", credentials);
}