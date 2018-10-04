import React from 'react';
import ReactDOM from 'react-dom';
import './wall.css';
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(header, token);
});
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
function textToLink(text){
var finalText=text.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
var urlPattern = /(http|ftp|https):\/\/[\w-]+(\.[\w-]+)+([\w.,@?^=%&amp;:\/~+#-]*[\w@?^=%&amp;\/~+#-])?/gi;
var htmlData=finalText.replace(urlPattern, '<a target="_blank" href="$&">$&</a>');
return htmlData;
}
class WallContainer extends React.Component{
    render(){
    return (
    <div id="wallContainer">
        <h1>Social Network System with React JS Demo</h1>
        <WallFeed />
    </div>);
    }
}
class WallFeed extends React.Component{
    constructor(props){
        super(props);
        this.state = {data:[]};
        this.updateAjaxSubmit = this.updateAjaxSubmit.bind(this);
        this.deleteUpdate = this.deleteUpdate.bind(this);
    }
    updateAjaxSubmit(update){
        ajaxPostReact("/api/updates",update,(data)=>{
            var updates = this.state.data;
            var newUpdates = [data.updates[0]].concat(updates);
            this.setState({data:newUpdates});
        })
    }
    updatesFromServer(){
        var dataPost ={};
        ajaxPostReact("/api/comments",dataPost,(data)=>{this.setState({data:data.updates})})
    }
    componentDidMount(){
        this.updatesFromServer();
    }
    deleteUpdate(e){
        var updateIndex = e.target.getAttribute('value');
        var update_id = e.target.getAttribute('data');
        var data = {update_id:update_id};
        ajaxPostReact("/api/deleteUpate",data,()=>{
            this.state.data.splice(updateIndex,1);
            this.setState({data:this.state.data});
        })
    }
    render(){
        return(
            <div>
                <WallForm onUpdateSubmit={this.updateAjaxSubmit} />
                <WallUpdates data = {this.state.data} deleteUpdate={this.deleteUpdate} />
            </div>
        );
    }
}
class WallForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {user_update:''};
        this.updateChange = this.updateChange.bind(this);
        this.updateSubmit = this.updateSubmit.bind(this);
    }
    componentDidMount(){
        ReactDOM.findDOMNode(this.refs.updateInput).focus();
    }
    updateChange(e){
        this.setState({user_update:e.target.value});
    }
    updateSubmit(e){
        e.preventDefault();
        var user_update = this.state.user_update.trim();
        if(!user_update){
            return;
        }else{
            this.props.onUpdateSubmit({user_update:user_update});
            this.setState({user_update:""});
        }
    }
    render(){
        return(
            <form onSubmit = {this.updateSubmit}>
                <textarea ref = "updateInput" value ={this.state.user_update}
                onChange={this.updateChange}></textarea>
                <input type='submit' value='Post' id='wallPost'/>
            </form>
        );
    }
}
class WallUpdates extends React.Component{
    render(){
        var updateEach = this.props.data.map((update,index)=>{
            return(
                <div className="feedBody" key={update.created}>
                    <img src={update.profile_pic} className="feedImg" />
                    <div className="feedText">
                        <b>{update.name}</b>
                        <a href="#" className="feedDelete" value={index} data={update.update_id}
                        onClick={this.props.deleteUpdate}>X</a>
                        <span dangerouslySetInnerHTML={{__html:textToLink(update.user_update)}}/>
                    </div>
                    <CommentBlock dataComments = {update.comments} updateID={update.update_id}/>
                </div>
            );
        })
        return(
            <div id = "wallFeed">
                {updateEach}
            </div>
        );
    }
}

class CommentBlock extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            showComment: false
        };
    }
    
    renderCommentForm(){
        if(this.state.showComment){
            return(<CommentForm/>);
        }
    }
    render(){
        return(<div>
            <div className="feedLinks">
                <a href="#" onClick={()=>{this.setState({showComment:!this.state.showComment})}}>
                Comment
                </a>
            </div>
            <CommentGrid dataComments = {this.props.dataComments}/>
            {this.renderCommentForm()}
        </div>);
    }
}

class CommentGrid extends React.Component{
    render(){
        var comments = this.props.dataComments.map((comment,index)=>{
            return(
                <div className = "feedCommentGrid" key = {comment.com_id}>
                    <img src={comment.profile_pic} className="commentImg"/>
                    <div className = "commentText">
                        <b>{comment.name}</b>
                        <a href="#" data={comment.com_id} value = {index}
                        className="commentDelete">X</a>
                        <div>
                            <span dangerouslySetInnerHTML={{__html:textToLink(comment.comment)}} />
                        </div>
                    </div>
                </div>
            );
        });

        return ( 
            <div id="commnetsFeed">
                {comments}
            </div>
        );
    }
}

class CommentForm extends React.Component{
    render(){
        return (
            <div className="feedCommentForm">
                <form>
                    <textarea className = "commentInput"></textarea>
                    <input type="submit" value="Comment" className="commentSubmit"/>
                </form>
            </div>
        );
    }
}
ReactDOM.render(<WallContainer />,document.getElementById('container'));