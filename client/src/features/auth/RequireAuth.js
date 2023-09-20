import { useLocation, Navigate, Outlet } from "react-router-dom"
import { useSelector } from "react-redux"
import { selectCurrentRefreshToken } from "./authSlice"

const RequireAuth = () => {
    const token = useSelector(selectCurrentRefreshToken)
    const location = useLocation()

    return (
        token
            ? <Outlet />
            : <Navigate to="/login" state={{ from: location }} replace />
    )
}
export default RequireAuth