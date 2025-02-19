import http from "@/lib/http";

export function getUser(id) {
  return http.get(`/dev/v1/user-profile/${id}`);
}
