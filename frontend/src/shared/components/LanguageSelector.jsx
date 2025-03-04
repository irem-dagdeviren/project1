import {useTranslation} from "react-i18next";

export function LanguageSelector(){
    const { i18n} = useTranslation();

    const onSelectLang = (language) => {
        i18n.changeLanguage(language)
        localStorage.setItem('lang', language)
    }
     return (
         <>
             <div className="d-flex align-items-center">
                 <img
                     role="button"
                     src="https://flagcdn.com/20x15/tr.png"
                     width="20"
                     height="15"
                     alt="Türkçe"
                     onClick={() => i18n.changeLanguage('tr')}
                     className="me-2"/>
                 <img role="button"
                      src="https://flagcdn.com/20x15/us.png"
                      width="20"
                      height="15"
                      alt="English"
                      onClick={() => i18n.changeLanguage('en')}
                      className="me-2"/>
             </div>
         </>
     )
}