export function PasswordInput(props) {

    const {id, label, error, onChange, type} = props

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
            <div className="input-group">
                <input id={id}
                       className={error ? "form-control is-invalid" : "form-control"}
                       onChange={onChange}
                       type={type}/>
                <button
                    type="button"
                    className="btn btn-outline-secondary"
                    onClick={() => togglePasswordVisibility(id)}>
                    <i className="bi bi-eye"></i>
                </button>
                <div className="invalid-feedback">
                    {error}
                </div>
            </div>
        </div>
    )
}