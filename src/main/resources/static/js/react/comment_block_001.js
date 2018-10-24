import React from 'react';
import ReactDOM from 'react-dom';
"use strict";

function ajaxPostReact(url, dataPost, success){
    $.ajax({
        type:"POST",
        url: url,
        data:JSON.stringify(dataPost),
        dataType:"json",
        contentType: "application/json",
        cache:false,
        timeout:20000,
        success:success,
        error:function(e){
		    alert("Error!");
		    console.log("ERROR: ",e);
	    }
    });
}

class CommentsContainer extends React.Component{
    constructor(props){
        super(props);
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
        });
        this.state = {comments :[]};
    }
    render(){
        return (
            <div id="comments" className="row">
                <div className="col-full">
                    <h3 className="h2">5 Comments</h3>
                    <CommentList comments = {this.state.comments}/>
                </div>
            </div>
        );
    }
    componentDidMount(){
        ajaxPostReact("/api/comments",{"article_id":article_id},(data)=>{
            this.setState({comments:data});
            console.log(data);});
    }
}
class CommentList extends React.Component{
    render(){
        return (
            <ol className="commentlist">
                {this.props.comments.map((comment)=>{
                    return(
                        <li className="depth-1 comment" key = {comment.id}>
                            <div className="comment__avatar">
                                <img width="50" height="50" className="avatar" src={comment.user.image==null?"images/avatars/user-03.jpg":comment.user.image} alt="" />
                            </div>

                            <div className="comment__content">

                                <div className="comment__info">
                                    <cite>{comment.user.nickName}</cite>

                                    <div className="comment__meta">
                                        <time className="comment__time">{comment.creation_date}</time>
                                        <a className="reply" href="#0">Reply</a>
                                    </div>
                                </div>

                                <div className="comment__text">
                                <p>{comment.context}</p>
                                </div>

                            </div>
                        </li>
                    );
                })}
            </ol>
        );
    }
}
ReactDOM.render(<CommentsContainer />,document.getElementById('commentsContainer'));
