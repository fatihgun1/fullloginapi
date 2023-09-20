import { useGetUsersQuery } from "./userApiSlice"

const UsersList = () => {
    const { data } = useGetUsersQuery()

    let content = (
        <section className="users">
            {data}
        </section>
    );

    return content
}
export default UsersList