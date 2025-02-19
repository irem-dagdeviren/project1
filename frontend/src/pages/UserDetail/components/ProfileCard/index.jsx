import defaultProfileImage from "@/assets/profile.png";
import {useAuthState} from "@/shared/state/context.jsx";
import {Button} from "@/shared/components/Button.jsx";

export function ProfileCard({ user }) {
    const authState = useAuthState()
  return (
      <div className="card">
          <div className="card-header text-center" >
              <img
                  src = {defaultProfileImage}
                  width = "200"
                  className= "img-fluid rounded-circle shadow-sm"
                  alt=""/>
          </div>
          <div className="card-body text-center">
              <span className="fs-3"> { user.username}</span>
              <br/>
              <span className="fs-3"> { user.id}</span>
              <span className="fs-3"> { authState.id}</span>

              {authState.id === user.authId && <Button> Edit</Button>}
          </div>
      </div>
  )
}
