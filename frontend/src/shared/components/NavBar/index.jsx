import {Link} from "react-router-dom";
import logo from "@/assets/logo.png";
import {useTranslation} from "react-i18next";
import {useAuthDispatch, useAuthState} from "@/shared/state/context.jsx";
import {logout} from "@/shared/components/NavBar/api.js";
import {ProfileImage} from "@/shared/components/ProfileImage.jsx";

export function Index(){
    const { t } = useTranslation();
    const authState = useAuthState()
    const dispatch = useAuthDispatch();

    const onClickLogout = async () => {
        try {
            await logout();
        } catch {

        } finally {
            dispatch({type: 'logout-success'});
        }
    }

    return <>
        <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">
                    <img src={logo} width={60}/>
                    {t('appName')}
                </Link>
                <ul className="navbar-nav">
                    {authState.id === 0 && (<>
                        <li className="nav-item">
                            <Link className="nav-link" to="/signup">
                                {t('signUp')}
                            </Link>
                        </li>

                        <li className="nav-item">
                            <Link className="nav-link" to="/login">
                                {t('login')}
                            </Link>
                        </li>
                    </>)}

                    {authState.id > 0 && (<>
                        <li className="nav-item">
                            <Link className="nav-link" to="/">
                                {t('homepage')}
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to={`/user/${authState.id}`}>
                                <ProfileImage width={30} />
                                <span className="ms-2">{authState.username}</span>
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to= "/login" role="button" onClick={onClickLogout}>Logout</Link>
                        </li>
                    </>)}
                </ul>
            </div>
        </nav>
    </>
}