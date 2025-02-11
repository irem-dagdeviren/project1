import './App.css'
import {Outlet} from "react-router-dom";
import {LanguageSelector} from "./shared/components/LanguageSelector.jsx";
import {useTranslation} from "react-i18next";
import {Navbar} from "./shared/components/Navbar.jsx";

function App() {
    const {t} = useTranslation();


    return (
        <>
            <Navbar/>
            <div className="container mt-3">
                <Outlet/>
                <LanguageSelector/>
            </div>
        </>
    )
}

export default App
