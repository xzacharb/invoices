from flask import Flask, jsonify, send_from_directory
from flask_sqlalchemy import SQLAlchemy
from os import path
from flask_login import LoginManager
import os

# app = Flask(__name__)
# app.config.from_object("project_auth.config.Config")
# db = SQLAlchemy(app)
basedir = os.path.abspath(os.path.dirname(__file__))
db = SQLAlchemy()
# DB_NAME = "database.db"


def create_app():
    app = Flask(__name__)
    app.config['SECRET_KEY'] = 'hjhkdfjhkjdsjdsbf djfhkdshf'
    # app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{DB_NAME}'
    app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv("DATABASE_URL", "sqlite://")
    # app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv("DATABASE_URL", "sqlite://{DB_NAME}")
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    app.config['STATIC_FOLDER'] = f"{os.getenv('APP_FOLDER')}/project_auth/static"
    db.init_app(app)

    from .views import views
    from .auth import auth

    app.register_blueprint(views, url_prefix='/')
    app.register_blueprint(auth, url_prefix='/')

    from .models import User, Note

    create_database(app)

    login_manager = LoginManager()
    login_manager.login_view = 'auth.login'
    login_manager.init_app(app)

    @login_manager.user_loader
    def load_user(id):
        return User.query.get(int(id))

    return app
# STATIC_FOLDER = f"{os.getenv('APP_FOLDER')}/project_auth"


def create_database(app):
    # if not path.exists('project_auth/' + DB_NAME):
    if not path.exists(f"{os.getenv('APP_FOLDER')}/project_auth"):
        db.create_all(app=app)
        print('Created Database!')

# @app.route("/static/<path:filename>")
# def staticfiles(filename):
#     return send_from_directory(app.config["STATIC_FOLDER"], filename)
