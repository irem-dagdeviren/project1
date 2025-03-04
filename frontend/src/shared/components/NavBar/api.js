import http from "@/lib/http";

export function logout(){
    return http.post("/dev/v1/auth/logout");
}