import './App.css'
import {Outlet} from "react-router-dom";
import {LanguageSelector} from "./shared/components/LanguageSelector.jsx";
import {useTranslation} from "react-i18next";
import {Index} from "./shared/components/NavBar/index.jsx";
import {AuthenticationContext} from "@/shared/state/context.jsx";

function App() {
    const {t} = useTranslation();


    return (
        <AuthenticationContext>
            <Index/>
            <div className="container mt-3">
                <Outlet/>
                <LanguageSelector/>
            </div>
        </AuthenticationContext>
    )
}

export default App
