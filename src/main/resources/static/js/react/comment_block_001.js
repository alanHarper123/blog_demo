import React from 'react';
import ReactDOM from 'react-dom';
import PropTypes from 'prop-types';
import SimpleAppBar from './simpleAppBar';
import Icon from './icons';

"use strict";

function commentIcon(marginLeft,iconName,description){
	return(
			<span style={{marginLeft: marginLeft==null?null:marginLeft, cursor:"pointer",  display: "inline-flex"}}>
        		<span style={{display: "inline-flex",alignItems: "center"}}>
        			<Icon iconName={iconName}/>
        		</span>
        		{description}
        	</span>	
	);
}
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
                    <ol className="commentlist">
                    {
                    	this.state.comments.map((comment)=>{
                    		return (<CommentList comment = {comment} key = {comment.id}/> 
                    				);
                    	})
                    	
                    }
                    
                    </ol>
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

		return(
				<li className="depth-1 comment" >
				<div className="comment__avatar">
				<img width="50" height="50" className="avatar" src={this.props.comment.user.image==null?"images/avatars/user-03.jpg":this.props.comment.user.image} alt="" />
					</div>
				<span>
				{commentIcon(null, "viewReply", 70)}
				</span>
				{commentIcon("10px", "reply","reply")}
				{commentIcon("10px", "upvote", 50)}
				{commentIcon("10px", "report")}  

				<div className="comment__content">

				<div className="comment__info">
				<cite>{this.props.comment.user.nickName}</cite>

				<div className="comment__meta">
				<time className="comment__time">{this.props.comment.creation_date}</time>
				<a className="reply" href="#0">Reply</a>
				</div>
				</div>

				<div className="comment__text">
				<p>{this.props.comment.context}</p>
				</div>

				</div>
				</li>
		);
	}
}
class subCommentList extends React.Component{
	render(){
		return(
				<li className="depth-2 comment" >
				<div className="comment__avatar">
				<img width="50" height="50" className="avatar" src={this.props.comment.user.image==null?"images/avatars/user-03.jpg":this.props.comment.user.image} alt="" />
					</div>
				<span>
				{commentIcon(null, "viewReply", 70)}
				</span>
				{commentIcon("10px", "reply","reply")}
				{commentIcon("10px", "upvote", 50)}
				{commentIcon("10px", "report")}  

				<div className="comment__content">

				<div className="comment__info">
				<cite>{this.props.comment.user.nickName}</cite>

				<div className="comment__meta">
				<time className="comment__time">{this.props.comment.creation_date}</time>
				<a className="reply" href="#0">Reply</a>
				</div>
				</div>

				<div className="comment__text">
				<p>{this.props.comment.context}</p>
				</div>

				</div>
				</li>
		);
	}
}
ReactDOM.render(<CommentsContainer />,document.getElementById('commentsContainer'));
