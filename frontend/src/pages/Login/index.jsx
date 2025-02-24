import {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {Alert} from "@/shared/components/Alert";
import {Input} from "@/shared/components/Input";
import {Button} from "@/shared/components/Button";
import {login} from "./api";
import {useAuthDispatch} from "@/shared/state/context";
import {Link, useNavigate} from "react-router-dom";
import {PasswordInput} from "@/shared/components/passwordInput.jsx";

export function Login() {
  const [email, setEmail] = useState();
  const [userName, setUserName] = useState();
  const [password, setPassword] = useState();
  const [apiProgress, setApiProgress] = useState();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const { t } = useTranslation();
  const navigate = useNavigate();
  const dispatch = useAuthDispatch();

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        userName: undefined,
      };
    });
  }, [userName]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setGeneralError();
    setErrors({})
    setApiProgress(true);

    try {
        const response = await login({ userName, password })
        dispatch({type: 'login-success', data: response.data})
        navigate("/")
    } catch (axiosError) {
        if (axiosError.response?.data) {
          if (axiosError.response.data.status === 400) {
            setErrors(axiosError.response.data.validationErrors);
          } else {
            setGeneralError(axiosError.response.data.message);
          }
        } else {
          setGeneralError(t(axiosError.message));
        }
    } finally {
        setApiProgress(false);
    }
  };

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form className="card" onSubmit={onSubmit}>
          <div className="text-center card-header">
            <h1>{t("login")}</h1>
          </div>
          <div className="card-body">
            <Input
              id="userName"
              label={t("username")}
              error={errors.userName}
              onChange={(event) => setUserName(event.target.value)}
            />
            <PasswordInput
                id="password"
                label={t('password')}
                error={errors.password} onChange={(event) => setPassword(event.target.value)}
                type = "password"  />
            {generalError && <Alert styleType="danger">{generalError}</Alert>}
            <div className="text-center">
              <Button disabled={!userName || !password} apiProgress={apiProgress}>
                {t("login")}
              </Button>
            </div>
          </div>
          <div className="card-footer text-center">
            <Link to="/password-reset/request">Forget password?</Link>
          </div>
        </form>
      </div>
    </div>
  );
}
