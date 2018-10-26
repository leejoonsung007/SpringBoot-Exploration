<!-- navigation bar -->
<header role="banner">
    <div style="z-index:9999">
        <nav role="navigation" class="navbar navbar-static-top navbar-default" style="margin-bottom:0px">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="{{ url_for('main.index') }}">
                        <img src="../../static/img/logo.png" alt="BestChoice'" width="120" style="margin-top:-9px">
                    </a>
                </div>

                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav pull-left">
                        <li><a href='{{ url_for('main.index') }}'>
                            <span class="glyphicon glyphicon-home"></span> Home
                            </a></li>
                        <li><a href='{{ url_for('main.result', like='%Dublin%', coordination ='53.3498123,-6.2624488') }}'>
                            <span class="glyphicon glyphicon-search"></span> Search
                            </a></li>
                        <li><a href='{{ url_for('main.rank') }}'>
                            <span class="glyphicon glyphicon-sort-by-order"></span> Rank
                            </a></li>
                        <li><a href='{{ url_for('operation.compare') }}'>
                            <span class="glyphicon glyphicon-tasks"></span> Compare
                            </a></li>
                    </ul>
                    <ul class="nav navbar-nav pull-right">
                        <li class="dropdown">
                            {% if current_user.is_authenticated %}
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                {% if current_user.photo %}
                                <img src="{{ current_user.photo }}" height="23" width="23">
                                {% else %}
                                <img class="img-rounded profile-thumbnail"
                                     src="../../static/avatars/default_avatar.jpg" height="23"
                                     width="23">
                                {% endif %}
                                {{ current_user.username }} <b class="caret"></b>
                            </a>

                            <ul class="dropdown-menu">
                                {% if current_user.login_type != 'website' %}
                                <li><a href="{{ url_for('operation.user', username=current_user.username) }}">Profile</a>
                                </li>
                                {#                                    <li><a href="{{ url_for('operation.following', username=current_user.username)}}">Watching List</a></li>#}
                                <li><a href="{{ url_for('auth.logout') }}">Log out</a></li>
                                {% else %}
                                <li><a href="{{ url_for('operation.user', username=current_user.username) }}">Profile</a>
                                </li>
                                {#                                    <li><a href="{{ url_for('operation.following', username=current_user.username)}}">Watching List</a></li>#}
                                <li><a href="{{ url_for('operation.change_password') }}">Change password</a>
                                </li>
                                <li><a href="{{ url_for('auth.logout') }}">Log out</a></li>
                                {% endif %}
                            </ul>

                        </li>
                        {% else %}
                        <li><a href="{{ url_for('auth.login',type ="index") }}">Log In</a></li>
                        {% endif %}
                    </ul>
                </div><!--/.nav-collapse -->
            </div><!--/.container -->
        </nav>
    </div>
</header>