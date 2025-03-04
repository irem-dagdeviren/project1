import {activateUser} from "./api.js";
import {Alert} from "@/shared/components/Alert.jsx";
import {Spinner} from "@/shared/components/Spinner.jsx";
import {useRouteParamApiRequest} from "@/shared/hooks/useRouteParamApiRequest.js";

export function Activation(){
    const { apiProgress, data, error } = useRouteParamApiRequest(
        "token",
        activateUser
    );
    return <>
        {apiProgress && (
            <Alert>
                <Spinner sm={true}></Spinner>
            </Alert>
        )}
        {error && <Alert styleType = "danger"> {error } </Alert>}
        {data?.message && <Alert styleType = "success"> {data.message} </Alert> }

    </>
}