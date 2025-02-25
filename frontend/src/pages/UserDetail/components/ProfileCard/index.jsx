import defaultProfileImage from "@/assets/profile.png";
import {useAuthState} from "@/shared/state/context.jsx";
import {Button} from "@/shared/components/Button.jsx";
import {useEffect, useState} from "react";
import {UserEditForm} from "@/pages/UserDetail/components/ProfileCard/UserEditForm.jsx";

export function ProfileCard({ user }) {
    const authState = useAuthState()
    const [editMode ,setEditMode] = useState(false)
    const [newUsername ,setNewUsername] = useState(authState.username)
    const [newMail ,setNewMail] = useState(authState.email)
    const [newPhone ,setNewPhone] = useState(authState.phone)
    const [errors, setErrors] = useState({});
    const isEditButtonVisible = !editMode && authState.id === user.authId

    useEffect(() => {
        setErrors(function (lastErrors){
            return {
                ...lastErrors,
                username: undefined
            }
        })
    }, [newUsername]);

    useEffect(() => {
        setErrors(function (lastErrors){
            return {
                ...lastErrors,
                email: undefined
            }
        })
    }, [newMail]);

    useEffect(() => {
        setErrors(function (lastErrors){
            return {
                ...lastErrors,
                phone: undefined
            }
        })
    }, [newPhone]);


    const visibleUsername = authState.id === user.authId ? authState.username : user.username
    const visibleEmail = authState.id === user.authId ? authState.email : user.email
    const visiblePhone = authState.id === user.authId ? authState.phone : user.phone

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
              <span className="fs-3 d-block "> {authState.id}</span>
              {!editMode &&
                  <div>
                      <span className="fs-3 d-block "> {visibleUsername}</span>
                      <span className="fs-3 d-block "> {visibleEmail}</span>
                      <span className="fs-3 d-block "> {visiblePhone}</span>
                  </div>
              }
              {isEditButtonVisible &&
                  <Button onClick={() => setEditMode(true)}>
                      Edit</Button>
              }

              {editMode &&
                  <UserEditForm setEditMode={setEditMode}/>
              }
          </div>
      </div>
  )
}
