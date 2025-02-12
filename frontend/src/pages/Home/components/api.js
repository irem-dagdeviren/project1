import http from "@/lib/http";

export function loadUsers(page = 0){
    return http.get("/dev/v1/user-profile/get-all", { params: { page, size: 3} });
}