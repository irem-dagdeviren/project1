export function Input(props) {

    const { id , label , error , onChange, type} = props

    const togglePasswordVisibility = (inputId) => {
        const input = document.getElementById(inputId);
        if (input) {
            input.type = input.type === "password" ? "text" : "password"
        }
    }
    return (
        <div className="mb-3 position-relative">
            <label htmlFor={id}
                   className="form-label">
                {label}
            </label>

            <input id={id}
                   className={error ? "form-control is-invalid" : "form-control"}
                   onChange={onChange}
                   type={type}/>
            <div className="invalid-feedback">
                {error}
            </div>
        </div>
    )
}