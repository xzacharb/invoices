# import os
# from werkzeug.utils import secure_filename
# from flask import (
#     Flask,
#     jsonify,
#     send_from_directory,
#     request,
#     redirect,
#     url_for
# )
# # from . import models
# from .models import db, Legal_forms, Contractor, Cities, Invoices, Evaluated, Roles, Company_management, Governement


# app = Flask(__name__)
# app.config.from_object("project_invoices.config.Config")


# @app.route("/")
# def hello_world():
#     return jsonify(hello="world")


# @app.route("/static/<path:filename>")
# def staticfiles(filename):
#     return send_from_directory(app.config["STATIC_FOLDER"], filename)


# @app.route("/media/<path:filename>")
# def mediafiles(filename):
#     return send_from_directory(app.config["MEDIA_FOLDER"], filename)


# @app.route("/upload", methods=["GET", "POST"])
# def upload_file():
#     if request.method == "POST":
#         file = request.files["file"]
#         filename = secure_filename(file.filename)
#         file.save(os.path.join(app.config["MEDIA_FOLDER"], filename))
#     return f"""{'''
#     <!doctype html>
#     <title>upload new File</title>
#     <h1>Upload new File</h1>
#     <form action="" method=post enctype=multipart/form-data>
#       <p><input type=file name=file><input type=submit value=Upload>
#     </form>'''}
#     """


# @app.route("/test", methods=["GET"])
# def test():
#     return {
#         "test": "test1"
#     }


# @app.route("/cities", methods=["GET"])
# def gcities():
#     allCities = Cities.query.all()
#     output = []
#     for city in allCities:
#         currCity = {}
#         currCity['name'] = city.name
#         output.append(currCity)
#     return jsonify(output)


# @app.route("/cities", methods=["POST"])
# def pcities():
#     citiesData = request.get_json()
#     city = Cities(name=citiesData['name'])
#     db.session.add(city)
#     db.session.commit()
#     return jsonify()
