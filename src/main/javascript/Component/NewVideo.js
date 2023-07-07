import React, {useState} from "react";
import debounce from 'lodash.debounce';
const NewVideo = () => {
    const [videoNume, setVideoNume] = useState("");
    const [isSubmit, setIsSubmit] = useState(false);
    const handleSubmit = async (event) => {
        event.preventDefault();
        setIsSubmit(true);
        await fetch("/api/videos", {
            method: "POST",
            headers:{
                "Content-type": "application/json"
            },
            body: JSON.stringify({nume: videoNume})
        }).then (() => window.location.href = "/react")
            .finally( () => setIsSubmit(false))

    }

    const onChange = (e) => {
        console.log("setVideoNume", e.target.value);
        setIsSubmit(false);
        setVideoNume(e.target.value);
    };



        const debouncedOnChange = debounce(onChange, 1000);

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    onChange={ (event) => {
                        setIsSubmit(true);
                        debouncedOnChange(event);
                    }}
                />
                <button type="submit" disabled={isSubmit}> Submit </button>
            </form>
            {console.log(videoNume)}
        </div>
    )
}

export default NewVideo;