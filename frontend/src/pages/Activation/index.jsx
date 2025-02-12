import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {activateUser} from "./api.js";
import {Alert} from "@/shared/components/Alert.jsx";
import {Spinner} from "@/shared/components/Spinner.jsx";
import {useTranslation} from "react-i18next";

export function Activation(){

    const { token} = useParams()
    const [apiProgress, setApiProgress] = useState()
    const [errors, setErrors] = useState({})
    const [successMessage, setSuccessMessage] = useState()
    const [generalErrors, setGeneralErrors] = useState()
    const { t } = useTranslation()
    useEffect(() => {
        async function activate(){
            setApiProgress(true)
            try{
                const response = await activateUser(token);
                setSuccessMessage(t('activationSuccessful'))

            }catch (axiosError){
                setGeneralErrors(axiosError.response.data.message)
            }finally {
                setApiProgress(false)
            }
        }
        activate();

    }, []);
    return <>
        {apiProgress && <Spinner sm={true}></Spinner>}
        {generalErrors && <Alert styleType = "danger"> {generalErrors } </Alert>}
        {successMessage && <Alert styleType = "success"> {successMessage} </Alert> }

    </>
}