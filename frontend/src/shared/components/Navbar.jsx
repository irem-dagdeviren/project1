import {Link} from "react-router-dom";
import logo from "@/assets/logo.png";
import {useTranslation} from "react-i18next";

export function Navbar(){
    const { t } = useTranslation();
    return <>
        <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">
                    <img src={logo} width={60}/>
                    {t('appName')}
                </Link>
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link" to="/signup">
                            {t('signUp')}
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/">
                            {t('about')}
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/">
                            {t('about')}
                        </Link>
                    </li>
                </ul>
            </div>
        </nav>
    </>
}