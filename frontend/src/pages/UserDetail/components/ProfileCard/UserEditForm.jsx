import { useAuthDispatch, useAuthState } from "@/shared/state/context";
import { useState } from "react";
import { useTranslation } from "react-i18next";
import { updateUser } from "./api";
import { Input } from "@/shared/components/Input";
import { Alert } from "@/shared/components/Alert";
import { Button } from "@/shared/components/Button";

export function UserEditForm({ setEditMode }) {
  const authState = useAuthState()
  const [newUsername ,setNewUsername] = useState(authState.username)
  const [newMail ,setNewMail] = useState(authState.email)
  const [newPhone ,setNewPhone] = useState(authState.phone)
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const [apiProgress ,setApiProgress] = useState(false)
  const { t } = useTranslation()
  const dispatch = useAuthDispatch();

  const onChangeUsername = (event) => {
    setNewUsername(event.target.value)
  }
  const onChangeEmail = (event) => {
    setNewMail(event.target.value)
  }
  const onChangePhone = (event) => {
    setNewPhone(event.target.value)
  }
  const onClickSave = async (event) => {
    event.preventDefault()
    setErrors({})
    setGeneralError()
    setApiProgress(true)
    try{
      await updateUser(authState.id, {username:newUsername  , email:newMail , phone:newPhone})
      dispatch({type: 'user-update-success', data: {username : newUsername, email: newMail , phone:newPhone}})
      setEditMode(false)
    }
    catch (axiosError) {
      console.error("Axios Error:", axiosError); // Log the entire error object
      if (axiosError.response?.data) {
        if (axiosError.response.data.status === 400) {
          setErrors(axiosError.response.data.validationErrors);
        } else {
          setGeneralError(axiosError.response.data.messsage);
        }
      } else {
        setGeneralError(t(axiosError.message));
      }
      console.log(axiosError.response.data)
    }finally {
      setApiProgress(false)
    }

  }
  const onClickCancel = async () => {
    setEditMode(false)
    setNewUsername(authState.username)
    setNewMail(authState.email)
    setNewPhone(authState.phone)
  }

  return (
    <form onSubmit={onClickSave}>
      <>
        <Input
            label = {t('username')}
            defaultValue = {authState.username}
            onChange = {onChangeUsername}
            error={errors.username} />
        <Input
            label = {t('email')}
            defaultValue = {authState.email}
            onChange = {onChangeEmail}
            error={errors.email} />
        <Input
            label = {t('phone')}
            defaultValue = {authState.phone}
            onChange = {onChangePhone}
            error={errors.phone} />
        {generalError && <Alert styleTyp
                                e="danger">{generalError}</Alert>}
        <Button
            apiProgress={apiProgress}
            type="submit"> {t('save')}
        </Button>
        <div className="d-inline m-1" />
        <Button
            styleType= "outline-secondary"
            onClick={onClickCancel}
            type="button">
          {t('cancel')}  </Button>
      </>
    </form>
  );
}
