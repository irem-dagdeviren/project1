import http from "@/lib/http";

export function updateUser(id, body){
    return http.put(`/dev/v1/user-profile/${id}`, body)
}