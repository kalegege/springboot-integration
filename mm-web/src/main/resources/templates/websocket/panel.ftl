<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <title>hello world</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/materialize.min.css"
          media="screen,projection"/>
    <link rel="stylesheet" href="${request.contextPath}/static/project/css/websocket/chat.css">
<#--<link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/ghpages-materialize.css"/>-->
</head>

<body>
<header>
    <nav>
        <div class="nav-wrapper row">
        <#--<a href="#!" class="brand-logo"><i class="material-icons">cloud</i>Logo</a>-->
            <form class="col s6">
                <div class="input-field">
                    <input id="search" type="search" required>
                    <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                    <i class="material-icons">close</i>
                </div>
            </form>
            <ul class="navbar-list right col s3 offset-s3">
                <li><a href="#!"><i class="material-icons">search</i></a></li>
                <li><a href="#!"><i class="material-icons">view_module</i></a></li>
                <li><a href="#!"><i class="material-icons">refresh</i></a></li>
                <li><a href="#!"><i class="material-icons">more_vert</i></a></li>
            </ul>
        </div>
    </nav>
</header>

<div id="main">
    <div class="row">
        <div class="content-wrapper-before blue-grey lighten-5"></div>
        <div class="col s12">
            <div class="container">
                <div class="chat-application">
                    <div class="chat-content-head">
                        <div class="header-details">
                            <h5 class="m-0 sidebar-title"><i class="material-icons app-header-icon text-top">mail_outline</i>
                                Chat</h5>
                        </div>
                    </div>
                    <div class="app-chat">
                        <div class="content-area content-right">
                            <div class="app-wrapper">
                                <!-- Sidebar menu for small screen -->
                                <a href="#" data-target="chat-sidenav" class="sidenav-trigger hide-on-large-only">
                                    <i class="material-icons">menu</i>
                                </a>
                                <!--/ Sidebar menu for small screen -->

                                <div class="card card card-default scrollspy border-radius-6 fixed-width">
                                    <div class="card-content chat-content p-0">
                                        <!-- Sidebar Area -->
                                        <div class="sidebar-left sidebar-fixed animate fadeUp animation-fast">
                                            <div class="sidebar animate fadeUp">
                                                <div class="sidebar-content">
                                                    <div id="sidebar-list"
                                                         class="sidebar-menu chat-sidebar list-group position-relative">
                                                        <div class="sidebar-list-padding app-sidebar" id="chat-sidenav">
                                                            <!-- Sidebar Header -->
                                                            <div class="sidebar-header">
                                                                <div class="row valign-wrapper">
                                                                    <div class="col s2 media-image pr-0">
                                                                        <img src="../../../app-assets/images/user/12.jpg"
                                                                             alt=""
                                                                             class="circle z-depth-2 responsive-img">
                                                                    </div>
                                                                    <div class="col s10">
                                                                        <p class="m-0 blue-grey-text text-darken-4 font-weight-700">
                                                                            Lawrence Collins</p>
                                                                        <p class="m-0 info-text">Apple pie bonbon
                                                                            cheesecake tiramisu</p>
                                                                    </div>
                                                                </div>
                                                                <span class="option-icon">
                          <i class="material-icons">more_vert</i>
                        </span>
                                                            </div>
                                                            <!--/ Sidebar Header -->

                                                            <!-- Sidebar Search -->
                                                            <div class="sidebar-search animate fadeUp">
                                                                <div class="search-area">
                                                                    <i class="material-icons mr-2 search-icon">search</i>
                                                                    <input type="text" placeholder="Search Chat"
                                                                           class="app-filter" id="chat_filter">
                                                                </div>
                                                                <div class="add-user">
                                                                    <a href="#">
                                                                        <i class="material-icons mr-2 add-user-icon">person_add</i>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                            <!--/ Sidebar Search -->

                                                            <!-- Sidebar Content List -->
                                                            <div class="sidebar-content sidebar-chat ps ps--active-y">
                                                                <div class="chat-list">
                                                                    <div class="chat-user animate fadeUp delay-1">
                                                                        <div class="user-section">
                                                                            <div class="row valign-wrapper">
                                                                                <div class="col s2 media-image online pr-0">
                                                                                    <img src="../../../app-assets/images/user/2.jpg"
                                                                                         alt=""
                                                                                         class="circle z-depth-2 responsive-img">
                                                                                </div>
                                                                                <div class="col s10">
                                                                                    <p class="m-0 blue-grey-text text-darken-4 font-weight-700">
                                                                                        Gorge Fernandis</p>
                                                                                    <p class="m-0 info-text">Apple pie
                                                                                        bonbon cheesecake tiramisu</p>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="info-section">
                                                                            <div class="star-timing">
                                                                                <div class="favorite">
                                                                                    <i class="material-icons amber-text">star</i>
                                                                                </div>
                                                                                <div class="time">
                                                                                    <span>2.38 pm</span>
                                                                                </div>
                                                                            </div>
                                                                            <span class="badge badge pill red">4</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="ps__rail-x" style="left: 0px; bottom: 0px;">
                                                                    <div class="ps__thumb-x" tabindex="0"
                                                                         style="left: 0px; width: 0px;"></div>
                                                                </div>
                                                                <div class="ps__rail-y"
                                                                     style="top: 0px; height: 389px; right: 0px;">
                                                                    <div class="ps__thumb-y" tabindex="0"
                                                                         style="top: 0px; height: 267px;"></div>
                                                                </div>
                                                            </div>
                                                            <!--/ Sidebar Content List -->
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/ Sidebar Area -->

                                        <!-- Content Area -->
                                        <div class="chat-content-area animate fadeUp">
                                            <!-- Chat header -->
                                            <div class="chat-header">
                                                <div class="row valign-wrapper">
                                                    <div class="col media-image online pr-0">
                                                        <img src="../../../app-assets/images/user/7.jpg" alt=""
                                                             class="circle z-depth-2 responsive-img">
                                                    </div>
                                                    <div class="col">
                                                        <p class="m-0 blue-grey-text text-darken-4 font-weight-700">
                                                            Alice Hawker</p>
                                                        <p class="m-0 chat-text truncate">Apple pie bonbon cheesecake
                                                            tiramisu</p>
                                                    </div>
                                                </div>
                                                <span class="option-icon">
                  <span class="favorite">
                    <i class="material-icons">star_outline</i>
                  </span>
                  <i class="material-icons">delete</i>
                  <i class="material-icons">more_vert</i>
                </span>
                                            </div>
                                            <!--/ Chat header -->

                                            <!-- Chat content area -->
                                            <div class="chat-area ps ps--active-y">
                                                <div class="chats">
                                                    <div class="chats">
                                                        <div class="chat chat-right">
                                                            <div class="chat-avatar">
                                                                <a class="avatar">
                                                                    <img src="../../../app-assets/images/user/12.jpg"
                                                                         class="circle" alt="avatar">
                                                                </a>
                                                            </div>
                                                            <div class="chat-body">
                                                                <div class="chat-text">
                                                                    <p>How can we help? We're here for you!</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="chat">
                                                            <div class="chat-avatar">
                                                                <a class="avatar">
                                                                    <img src="../../../app-assets/images/user/7.jpg"
                                                                         class="circle" alt="avatar">
                                                                </a>
                                                            </div>
                                                            <div class="chat-body">
                                                                <div class="chat-text">
                                                                    <p>Hey John, I am looking for the best admin
                                                                        template. Could you please help me to find it
                                                                        out?</p>
                                                                </div>
                                                                <div class="chat-text">
                                                                    <p>It should be material css compatible.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="chat chat-right">
                                                            <div class="chat-avatar">
                                                                <a class="avatar">
                                                                    <img src="../../../app-assets/images/user/12.jpg"
                                                                         class="circle" alt="avatar">
                                                                </a>
                                                            </div>
                                                            <div class="chat-body">
                                                                <div class="chat-text">
                                                                    <p>Absolutely!</p>
                                                                </div>
                                                                <div class="chat-text">
                                                                    <p>Materialize admin is the responsive material
                                                                        admin template.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="chat">
                                                            <div class="chat-avatar">
                                                                <a class="avatar">
                                                                    <img src="../../../app-assets/images/user/7.jpg"
                                                                         class="circle" alt="avatar">
                                                                </a>
                                                            </div>
                                                            <div class="chat-body">
                                                                <div class="chat-text">
                                                                    <p>Looks clean and fresh UI.</p>
                                                                </div>
                                                                <div class="chat-text">
                                                                    <p>It's perfect for my next project.</p>
                                                                </div>
                                                                <div class="chat-text">
                                                                    <p>How can I purchase it?</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="chat chat-right">
                                                            <div class="chat-avatar">
                                                                <a class="avatar">
                                                                    <img src="../../../app-assets/images/user/12.jpg"
                                                                         class="circle" alt="avatar">
                                                                </a>
                                                            </div>
                                                            <div class="chat-body">
                                                                <div class="chat-text">
                                                                    <p>Thanks, from ThemeForest.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="chat">
                                                            <div class="chat-avatar">
                                                                <a class="avatar">
                                                                    <img src="../../../app-assets/images/user/7.jpg"
                                                                         class="circle" alt="avatar">
                                                                </a>
                                                            </div>
                                                            <div class="chat-body">
                                                                <div class="chat-text">
                                                                    <p>I will purchase it for sure.</p>
                                                                </div>
                                                                <div class="chat-text">
                                                                    <p>Thanks.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="chat chat-right">
                                                            <div class="chat-avatar">
                                                                <a class="avatar">
                                                                    <img src="../../../app-assets/images/user/12.jpg"
                                                                         class="circle" alt="avatar">
                                                                </a>
                                                            </div>
                                                            <div class="chat-body">
                                                                <div class="chat-text">
                                                                    <p>Great, Feel free to get in touch on</p>
                                                                </div>
                                                                <div class="chat-text">
                                                                    <p>https://pixinvent.ticksy.com/</p>
                                                                </div>
                                                                <div class="chat-text"><p>234</p></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="ps__rail-x" style="left: 0px; bottom: -423px;">
                                                    <div class="ps__thumb-x" tabindex="0"
                                                         style="left: 0px; width: 0px;"></div>
                                                </div>
                                                <div class="ps__rail-y" style="top: 423px; height: 389px; right: 0px;">
                                                    <div class="ps__thumb-y" tabindex="0"
                                                         style="top: 203px; height: 186px;"></div>
                                                </div>
                                            </div>
                                            <!--/ Chat content area -->

                                            <!-- Chat footer <-->
                                            <div class="chat-footer">
                                                <form onsubmit="enter_chat();" action="javascript:void(0);"
                                                      class="chat-input">
                                                    <input type="text" placeholder="Type message here.."
                                                           class="message">
                                                    <a class="btn waves-effect waves-light send"
                                                       onclick="enter_chat();">Send</a>
                                                </form>
                                            </div>
                                            <!--/ Chat footer -->
                                        </div>
                                        <!--/ Content Area -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!-- START RIGHT SIDEBAR NAV -->
                <aside id="right-sidebar-nav">
                    <div id="slide-out-right"
                         class="slide-out-right-sidenav sidenav rightside-navigation right-aligned">
                        <div class="row">
                            <div class="slide-out-right-title">
                                <div class="col s12 border-bottom-1 pb-0 pt-1">
                                    <div class="row">
                                        <div class="col s2 pr-0 center">
                                            <i class="material-icons vertical-text-middle"><a href="#"
                                                                                              class="sidenav-close">clear</a></i>
                                        </div>
                                        <div class="col s10 pl-0">
                                            <ul class="tabs">
                                                <li class="tab col s4 p-0">
                                                    <a href="#messages" class="active">
                                                        <span>Messages</span>
                                                    </a>
                                                </li>
                                                <li class="tab col s4 p-0">
                                                    <a href="#settings">
                                                        <span>Settings</span>
                                                    </a>
                                                </li>
                                                <li class="tab col s4 p-0">
                                                    <a href="#activity">
                                                        <span>Activity</span>
                                                    </a>
                                                </li>
                                                <li class="indicator" style="left: 0px; right: 188px;"></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="slide-out-right-body ps ps--active-y">
                                <div id="messages" class="col s12 active">
                                    <div class="collection border-none">
                                        <input class="header-search-input mt-4 mb-2" type="text" name="Search"
                                               placeholder="Search Messages">
                                        <ul class="collection p-0">
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Elizabeth Elliott</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Thank
                                                        you</p>
                                                </div>
                                                <span class="secondary-content medium-small">5.00 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-1.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Mary Adams</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Hello
                                                        Boo</p>
                                                </div>
                                                <span class="secondary-content medium-small">4.14 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-off avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-2.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Caleb Richards</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Hello
                                                        Boo</p>
                                                </div>
                                                <span class="secondary-content medium-small">4.14 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-3.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Caleb Richards</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Keny
                                                        !</p>
                                                </div>
                                                <span class="secondary-content medium-small">9.00 PM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-4.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">June Lane</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Ohh
                                                        God</p>
                                                </div>
                                                <span class="secondary-content medium-small">4.14 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-off avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-5.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Edward Fletcher</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Love
                                                        you</p>
                                                </div>
                                                <span class="secondary-content medium-small">5.15 PM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-6.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Crystal Bates</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Can
                                                        we</p>
                                                </div>
                                                <span class="secondary-content medium-small">8.00 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-off avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Nathan Watts</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">
                                                        Great!</p>
                                                </div>
                                                <span class="secondary-content medium-small">9.53 PM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-off avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-8.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Willard Wood</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Do it</p>
                                                </div>
                                                <span class="secondary-content medium-small">4.20 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-1.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Ronnie Ellis</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Got
                                                        that</p>
                                                </div>
                                                <span class="secondary-content medium-small">5.20 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-9.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Daniel Russell</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Thank
                                                        you</p>
                                                </div>
                                                <span class="secondary-content medium-small">12.00 AM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-off avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-10.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Sarah Graves</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Okay
                                                        you</p>
                                                </div>
                                                <span class="secondary-content medium-small">11.14 PM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-off avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-11.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Andrew Hoffman</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Can
                                                        do</p>
                                                </div>
                                                <span class="secondary-content medium-small">7.30 PM</span>
                                            </li>
                                            <li class="collection-item sidenav-trigger display-flex avatar pl-5 pb-0"
                                                data-target="slide-out-chat">
                        <span class="avatar-status avatar-online avatar-50"><img
                                src="../../../app-assets/images/avatar/avatar-12.png" alt="avatar">
                           <i></i>
                        </span>
                                                <div class="user-content">
                                                    <h6 class="line-height-0">Camila Lynch</h6>
                                                    <p class="medium-small blue-grey-text text-lighten-3 pt-3">Leave
                                                        it</p>
                                                </div>
                                                <span class="secondary-content medium-small">2.00 PM</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div id="settings" class="col s12" style="display: none;">
                                    <p class="mt-8 mb-0 ml-5 font-weight-900">GENERAL SETTINGS</p>
                                    <ul class="collection border-none">
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Notifications</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input checked="" type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Show recent activity</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Show recent activity</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Show Task statistics</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Show your emails</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Email Notifications</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input checked="" type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                    <p class="mt-8 mb-0 ml-5 font-weight-900">SYSTEM SETTINGS</p>
                                    <ul class="collection border-none">
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>System Logs</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Error Reporting</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Applications Logs</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input checked="" type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Backup Servers</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="collection-item border-none mt-3">
                                            <div class="m-0">
                                                <span>Audit Logs</span>
                                                <div class="switch right">
                                                    <label>
                                                        <input type="checkbox">
                                                        <span class="lever"></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div id="activity" class="col s12" style="display: none;">
                                    <div class="activity">
                                        <p class="mt-5 mb-0 ml-5 font-weight-900">SYSTEM LOGS</p>
                                        <ul class="collection with-header">
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    Homepage mockup design <span
                                                        class="secondary-content">Just now</span>
                                                </div>
                                                <p class="mt-0 mb-2">Melissa liked your activity.</p>
                                                <span class="new badge amber" data-badge-caption="Important"> </span>
                                            </li>
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    Melissa liked your activity Drinks. <span class="secondary-content">10 mins</span>
                                                </div>
                                                <p class="mt-0 mb-2">Here are some news feed interactions concepts.</p>
                                                <span class="new badge light-green"
                                                      data-badge-caption="Resolved"></span>
                                            </li>
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    12 new users registered <span
                                                        class="secondary-content">30 mins</span>
                                                </div>
                                                <p class="mt-0 mb-2">Here are some news feed interactions concepts.</p>
                                            </li>
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    Tina is attending your activity <span class="secondary-content">2 hrs</span>
                                                </div>
                                                <p class="mt-0 mb-2">Here are some news feed interactions concepts.</p>
                                            </li>
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    Josh is now following you <span
                                                        class="secondary-content">5 hrs</span>
                                                </div>
                                                <p class="mt-0 mb-2">Here are some news feed interactions concepts.</p>
                                                <span class="new badge red" data-badge-caption="Pending"></span>
                                            </li>
                                        </ul>
                                        <p class="mt-5 mb-0 ml-5 font-weight-900">APPLICATIONS LOGS</p>
                                        <ul class="collection with-header">
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    New order received urgent <span
                                                        class="secondary-content">Just now</span>
                                                </div>
                                                <p class="mt-0 mb-2">Melissa liked your activity.</p>
                                            </li>
                                            <li class="collection-item">
                                                <div class="font-weight-900">System shutdown. <span
                                                        class="secondary-content">5 min</span></div>
                                                <p class="mt-0 mb-2">Here are some news feed interactions concepts.</p>
                                                <span class="new badge blue" data-badge-caption="Urgent"> </span>
                                            </li>
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    Database overloaded 89% <span
                                                        class="secondary-content">20 min</span>
                                                </div>
                                                <p class="mt-0 mb-2">Here are some news feed interactions concepts.</p>
                                            </li>
                                        </ul>
                                        <p class="mt-5 mb-0 ml-5 font-weight-900">SERVER LOGS</p>
                                        <ul class="collection with-header">
                                            <li class="collection-item">
                                                <div class="font-weight-900">System error <span
                                                        class="secondary-content">10 min</span></div>
                                                <p class="mt-0 mb-2">Melissa liked your activity.</p>
                                            </li>
                                            <li class="collection-item">
                                                <div class="font-weight-900">
                                                    Production server down. <span class="secondary-content">1 hrs</span>
                                                </div>
                                                <p class="mt-0 mb-2">Here are some news feed interactions concepts.</p>
                                                <span class="new badge blue" data-badge-caption="Urgent"></span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="ps__rail-x" style="left: 0px; bottom: 0px;">
                                    <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
                                </div>
                                <div class="ps__rail-y" style="top: 0px; height: 729px; right: 0px;">
                                    <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 409px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Slide Out Chat -->
                    <ul id="slide-out-chat" class="sidenav slide-out-right-sidenav-chat right-aligned">
                        <li class="center-align pt-2 pb-2 sidenav-close chat-head">
                            <a href="#!"><i class="material-icons mr-0">chevron_left</i>Elizabeth Elliott</a>
                        </li>
                        <li class="chat-body">
                            <ul class="collection ps ps--active-y">
                                <li class="collection-item display-flex avatar pl-5 pb-0" data-target="slide-out-chat">
               <span class="avatar-status avatar-online avatar-50"><img
                       src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
               </span>
                                    <div class="user-content speech-bubble">
                                        <p class="medium-small">hello!</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar justify-content-end pl-5 pb-0"
                                    data-target="slide-out-chat">
                                    <div class="user-content speech-bubble-right">
                                        <p class="medium-small">How can we help? We're here for you!</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar pl-5 pb-0" data-target="slide-out-chat">
               <span class="avatar-status avatar-online avatar-50"><img
                       src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
               </span>
                                    <div class="user-content speech-bubble">
                                        <p class="medium-small">I am looking for the best admin template.?</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar justify-content-end pl-5 pb-0"
                                    data-target="slide-out-chat">
                                    <div class="user-content speech-bubble-right">
                                        <p class="medium-small">Materialize admin is the responsive materializecss admin
                                            template.</p>
                                    </div>
                                </li>

                                <li class="collection-item display-grid width-100 center-align">
                                    <p>8:20 a.m.</p>
                                </li>

                                <li class="collection-item display-flex avatar pl-5 pb-0" data-target="slide-out-chat">
               <span class="avatar-status avatar-online avatar-50"><img
                       src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
               </span>
                                    <div class="user-content speech-bubble">
                                        <p class="medium-small">Ohh! very nice</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar justify-content-end pl-5 pb-0"
                                    data-target="slide-out-chat">
                                    <div class="user-content speech-bubble-right">
                                        <p class="medium-small">Thank you.</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar pl-5 pb-0" data-target="slide-out-chat">
               <span class="avatar-status avatar-online avatar-50"><img
                       src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
               </span>
                                    <div class="user-content speech-bubble">
                                        <p class="medium-small">How can I purchase it?</p>
                                    </div>
                                </li>

                                <li class="collection-item display-grid width-100 center-align">
                                    <p>9:00 a.m.</p>
                                </li>

                                <li class="collection-item display-flex avatar justify-content-end pl-5 pb-0"
                                    data-target="slide-out-chat">
                                    <div class="user-content speech-bubble-right">
                                        <p class="medium-small">From ThemeForest.</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar justify-content-end pl-5 pb-0"
                                    data-target="slide-out-chat">
                                    <div class="user-content speech-bubble-right">
                                        <p class="medium-small">Only $24</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar pl-5 pb-0" data-target="slide-out-chat">
               <span class="avatar-status avatar-online avatar-50"><img
                       src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
               </span>
                                    <div class="user-content speech-bubble">
                                        <p class="medium-small">Ohh! Thank you.</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar pl-5 pb-0" data-target="slide-out-chat">
               <span class="avatar-status avatar-online avatar-50"><img
                       src="../../../app-assets/images/avatar/avatar-7.png" alt="avatar">
               </span>
                                    <div class="user-content speech-bubble">
                                        <p class="medium-small">I will purchase it for sure.</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar justify-content-end pl-5 pb-0"
                                    data-target="slide-out-chat">
                                    <div class="user-content speech-bubble-right">
                                        <p class="medium-small">Great, Feel free to get in touch on</p>
                                    </div>
                                </li>
                                <li class="collection-item display-flex avatar justify-content-end pl-5 pb-0"
                                    data-target="slide-out-chat">
                                    <div class="user-content speech-bubble-right">
                                        <p class="medium-small">https://pixinvent.ticksy.com/</p>
                                    </div>
                                </li>
                                <div class="ps__rail-x" style="left: 0px; bottom: -504px;">
                                    <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
                                </div>
                                <div class="ps__rail-y" style="top: 504px; height: 658px; right: 0px;">
                                    <div class="ps__thumb-y" tabindex="0" style="top: 286px; height: 372px;"></div>
                                </div>
                            </ul>
                        </li>
                        <li class="center-align chat-footer">
                            <form class="col s12" onsubmit="slide_out_chat()" action="javascript:void(0);">
                                <div class="input-field">
                                    <input id="icon_prefix" type="text" class="search">
                                    <label for="icon_prefix">Type here..</label>
                                    <a onclick="slide_out_chat()"><i class="material-icons prefix">send</i></a>
                                </div>
                            </form>
                        </li>
                    </ul>
                </aside>
                <!-- END RIGHT SIDEBAR NAV -->

            </div>
        </div>
    </div>
</div>

<!--JavaScript at end of body for optimized loading-->
<footer class="page-footer footer footer-static footer-dark gradient-45deg-indigo-purple gradient-shadow navbar-border navbar-shadow">
    <div class="footer-copyright">
        <div class="container"><span>© 2019          <a
                href="http://themeforest.net/user/pixinvent/portfolio?ref=pixinvent" target="_blank">PIXINVENT</a> All rights reserved.</span><span
                class="right hide-on-small-only">Design and Developed by <a href="https://pixinvent.com/">PIXINVENT</a></span>
        </div>
    </div>
</footer>
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/materialize.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/project/js/websocket/chat.js"></script>

</html>