import React, {useEffect, useState} from 'react'
import ListOfVideos from "./Component/ListOfVideos";
import NewVideo from "./Component/NewVideo";
const App = () => {

    const [videoList, setVideoList] = useState([]);

    const getVideos = async () => {
        try{
            const response = await fetch("/api/videos");
            const data = await response.json();

            setVideoList(data);
        }catch (err){
            console.log(err);
        }
    }

    useEffect( () => {
        getVideos();
    }, []);

    return (
        <div>
            <ListOfVideos videoList={videoList}/>
            <NewVideo/>
        </div>
    )
}

export default App;