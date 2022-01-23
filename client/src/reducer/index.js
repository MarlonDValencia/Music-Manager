import * as actions from '../actions/index.js';

export const initialState = {

    loading: true,
    hasErrors: false,
    playlist: [],
    search: "",

}

export default function rootReducer(state = initialState, action) {

    switch (action.type) {

        case actions.LOADING:
            return { ...state, loading: true };

        case actions.LOADED_SUCCESS:

            var data = action.payload[0].song;
            var names = Object.keys(data);
            var songs = [];
            console.log(songs)
            for (let i = 0; i < names.length; i++) {
                for (const song in data) {
                    if(song === names[i]){
                      songs.push(data[song]);
                    }
                }
            }
            return { ...state, playlist :songs , loading: false, hasErrors: false };

        case actions.LOADED_FAILURE:
            return { ...state, loading: false, hasErrors: true }

        default:
            return state;
    }
}