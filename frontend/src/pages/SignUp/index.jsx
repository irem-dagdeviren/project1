import {useEffect, useMemo, useState} from "react";
import {signUp} from "./api.js";
import {Input} from "./component/input.jsx";
import {PasswordInput} from "./component/passwordInput.jsx";
import {useTranslation} from "react-i18next";
import {Alert} from "@/shared/components/Alert";
import {Spinner} from "@/shared/components/Spinner";

export function SignUp() {

    const [username, setUsername] = useState()
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [repassword, setRepassword] = useState()
    const [apiProgress, setApiProgress] = useState()
    const [errors, setErrors] = useState({})
    const [successMessage, setSuccessMessage] = useState()
    const [generalErrors, setGeneralErrors] = useState()

    const [ t ] = useTranslation()

    useEffect(() => {
        setErrors(function (lastErrors){
            return {
                    ...lastErrors,
                    username: undefined
            }
        })
    }, [username]);

    useEffect(() => {
            setErrors(function (lastErrors){
                return {
                        ...lastErrors,
                        email: undefined
                }
            })
        }, [email]);

    useEffect(() => {
            setErrors(function (lastErrors){
                return {
                        ...lastErrors,
                        password: undefined
                }
            })
        }, [password]);

    useEffect(() => {
            setErrors(function (lastErrors){
                return {
                        ...lastErrors,
                    repassword: undefined
                }
            })
        }, [repassword]);



    const onSubmit = async (event) => {
        event.preventDefault()
        setApiProgress(true)
        setGeneralErrors(null)
        setSuccessMessage(null)
        try {
            const response = await signUp({
                username, email, password
            })
            setSuccessMessage(t('createSuccesful'))
        } catch ( axiosError ){
            if(axiosError.response?.data){
                if(axiosError.response.data.status === 400){
                    setErrors(axiosError.response.data.validationErrors)
                }else{
                    setGeneralErrors(axiosError.response.data.message)
                }
            }
            else{
                setGeneralErrors(t('generalError'))
            }
        } finally {
            setApiProgress(false)
        }
    }

    // mismatching password hook
    const passwordRepeatError = useMemo(() => {
        if(password && password !== repassword){
            return t('passwordMismatch')
        }
    }, [password, repassword]);

    return (
        <div className="container d-flex justify-content-center align-items-center min-vh-100">
            <div className="col-lg-5 col-md-7 col-sm-10">
                <div className="card shadow-lg border-0 rounded-4">
                    <div className="card-header text-center bg-primary text-white py-4 rounded-top">
                        <h2 className="fw-bold">{t('signUp')}</h2>
                    </div>
                    <form className="card-body p-4" onSubmit={onSubmit}>
                        <Input id="username"
                               label={t('username')}
                               error={errors.username}
                               onChange={(event) => setUsername(event.target.value)} />
                        <Input id="email"
                               label={t('email')}
                               error={errors.email}
                               onChange={(event) => setEmail(event.target.value)} />
                        <PasswordInput id="password"
                                       label={t('password')}
                                       error={errors.password} onChange={(event) => setPassword(event.target.value)}
                                       type = "password"  />
                        <PasswordInput id="repassword"
                                       label={t('repassword')}
                                       error={passwordRepeatError} onChange={(event) => setRepassword(event.target.value)} t
                                       ype = "password" />

                        {/* Error / Success Messages */}
                        {generalErrors && <Alert styleType = "danger"> {generalErrors } </Alert>}
                        {successMessage && <Alert styleType = "success"> {successMessage} </Alert> }
                        <div className="text-center mt-4">
                            <button className="btn btn-primary w-100 py-2 fw-semibold">
                                    {apiProgress && <Spinner sm={true}></Spinner>}
                                DONE
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )

}