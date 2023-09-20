import { useSelector } from "react-redux"
import { selectCurrentToken, selectCurrentRefreshToken } from "./authSlice"
import { Link, useNavigate } from "react-router-dom"
import { logOut } from '../../features/auth/authSlice'
import { useEffect } from "react"
const Welcome = () => {
    {/*Session kullanıcı alma*/ }
    const navigate = useNavigate();
    const user = useSelector(selectCurrentToken)
    const token = useSelector(selectCurrentRefreshToken)
    const logOutHere = e => {
        logOut();
        navigate('/login')
    }
    const content = (
        <section className="welcome">
            <p>Welcome you login</p>

            <button onClick={() => logOutHere()}>Logout</button> 
            <Link to="/userList">USer</Link>
         </section>

    )

    return content
}
export default Welcome