import React from "react";

const ListOfVideos = ({videoList}) => {
    return (
        <div>
                {videoList?.map( (video) => {
                    return <li>{video.name}</li>
                })}
        </div>
    );

}

export default ListOfVideos;
