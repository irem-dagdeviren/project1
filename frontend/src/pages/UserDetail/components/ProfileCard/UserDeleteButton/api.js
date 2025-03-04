import http from "@/lib/http";

export function deleteUser(id){
    return http.delete(`/dev/v1/user-profile/${id}`)
}