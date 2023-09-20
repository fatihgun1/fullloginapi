import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import { setCredentials, logOut } from '../../features/auth/authSlice'

const baseQuery = fetchBaseQuery({
    baseUrl: 'http://localhost:8080/api/v1',
    credentials: 'same-origin',
    prepareHeaders: (headers, { getState }) => {
        const token = getState().auth.token;
        if (token) {
            headers.set("authorization", `Bearer ${token}`);

            return headers;
        }
    }
})

const baseQueryWithReauth = async (args, api, extraOptions) => {
    let result = await baseQuery(args, api, extraOptions);
    console.log(result)
    if (result?.error?.status === 403) {
        console.log('sending refresh token')
        const refreshResult = await baseQuery("/auth/refresh", api, extraOptions)

        console.log("Refresh token created");

        if (refreshResult?.data) {
            const token = api.getState().auth.token;
            api.dispatch(setCredentials({ ...refreshResult.data, token }));
            result = await baseQuery(args, api, extraOptions)
        } else {
            api.dispatch(logOut())
        }
    }
    return result;
}

export const apiSlice = createApi({
    baseQuery : baseQueryWithReauth,
    endpoints : builder => ({})
})