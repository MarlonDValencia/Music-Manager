export const LOADING = 'LOADING';
export const LOADED_SUCCESS = 'LOADED_SUCCESS';
export const LOADED_FAILURE = 'LOADED_FAILURE';

const URL_BASE = "http://localhost:8080/api/playlist";


export const loading = () => ({ type: LOADING });

export const success = (payload) => ({
    type: LOADED_SUCCESS,
    payload
});

export const failure = () => ({ type: LOADED_FAILURE })

export function fetchPlaylist() {
    return async dispatch => {
        dispatch(loading())
        try {
            const response = await fetch(
                `${URL_BASE}/xxxx-xxxx`
            )
            const data = await response.json()
            dispatch(success(data))
        } catch (error) {
            dispatch(failure())
        }
    }
}