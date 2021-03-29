import os

from werkzeug.utils import secure_filename
from flask import (
    Flask,
    jsonify,
    send_from_directory,
    request,
    redirect,
    url_for
)
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.sql import func
# from sqlalchemy import Column, Integer, DateTime, String, Text


app = Flask(__name__)
app.config.from_object("project_invoices.config.Config")
db = SQLAlchemy(app)


class Legal_forms(db.Model):
    __tablename__ = "legal_forms"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255), nullable=False)
    short = db.Column(db.String(255), nullable=False)
    contractor = db.relationship("Contractor", backref=db.backref("legal_forms", uselist=False))

    def __init__(self, name, short):
        self.name = name
        self.short = short


contractor_city = db.Table(
    'contractor_city',
    db.Column('city_id', db.Integer, db.ForeignKey('cities.id')),
    db.Column('contractor_id', db.Integer, db.ForeignKey('contractor.id'))
)

contractor_management = db.Table(
    'contractor_management',
    db.Column('management_id', db.Integer, db.ForeignKey('company_management.id')),
    db.Column('contractor_id', db.Integer, db.ForeignKey('contractor.id'))
)


class Contractor(db.Model):
    __tablename__ = "contractor"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255), nullable=False)
    adress = db.Column(db.String(255), nullable=False)
    description = db.Column(db.Text, nullable=False)
    date_created = db.Column(db.DateTime(timezone=True), server_default=func.now())
    source = db.Column(db.String(255), nullable=False)
    ico = db.Column(db.String(255), nullable=True)
    id_leg_form = db.Column(db.Integer, db.ForeignKey('legal_forms.id'))
    invoices = db.relationship("Invoices", backref=db.backref("contractor"))

    def __init__(self, name, adress, description, date_created, source, ico, id_leg_form):
        self.name = name
        self.adress = adress
        self.description = description
        self.date_created = date_created
        self.source = source
        self.ico = ico
        self.id_leg_form = id_leg_form


class Cities(db.Model):
    __tablename__ = "cities"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255), nullable=False)
    contractors = db.relationship("Contractor", secondary=contractor_city)
    invoices = db.relationship("Invoices", backref=db.backref("cities"))
    comp_manag = db.relationship("Company_management", backref=db.backref("cities"))
    governement = db.relationship("Governement", backref=db.backref("cities"))

    def __init__(self, name):
        self.name = name


class Invoices(db.Model):
    __tablename__ = "invoices"

    id = db.Column(db.Integer, primary_key=True)
    subject = db.Column(db.String(255), nullable=False)
    description = db.Column(db.Text, nullable=False)
    price = db.Column(db.Integer, nullable=False)
    date1 = db.Column(db.Date(), nullable=False)
    date2 = db.Column(db.Date(), nullable=False)
    date3 = db.Column(db.Date(), nullable=False)
    source = db.Column(db.String(255), nullable=False)
    id_contractor = db.Column(db.Integer, db.ForeignKey('contractor.id'))
    id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
    evaluated = db.relationship("Evaluated", backref=db.backref("invoices", uselist=False))

    def __init__(self, subject, description, price, date1, date2, date3, source, id_contractor, id_city):
        self.subject = subject
        self.description = description
        self.price = price
        self.date1 = date1
        self.date2 = date2
        self.date3 = date3
        self.source = source
        self.id_contractor = id_contractor
        self.id_city = id_city


class Evaluated(db.Model):
    __tablename__ = "evaluated"

    id = db.Column(db.Integer, primary_key=True)
    value = db.Column(db.String(255), nullable=False)
    description = db.Column(db.Text, nullable=False)
    evaluation = db.Column(db.String(255), nullable=True)
    id_invoice = db.Column(db.Integer, db.ForeignKey('invoices.id'))

    def __init__(self, value, description, evaluation, id_invoice):
        self.value = value
        self.description = description
        self.evaluation = evaluation
        self.id_invoice = id_invoice


class Roles(db.Model):
    __tablename__ = "roles"

    id = db.Column(db.Integer, primary_key=True)
    role_name = db.Column(db.String(255), nullable=False)
    comp_manag = db.relationship("Company_management", backref=db.backref("roles"))
    governement = db.relationship("Governement", backref=db.backref("roles"))

    def __init__(self, role_name):
        self.role_name = role_name


class Company_management(db.Model):
    __tablename__ = "company_management"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(50), nullable=False)
    middle_name = db.Column(db.String(100), nullable=True)
    last_name = db.Column(db.String(50), nullable=False)
    address = db.Column(db.String(255), nullable=True)
    date_start = db.Column(db.Date(), nullable=False)
    source = db.Column(db.String(255), nullable=False)
    contractors = db.relationship("Contractor", secondary=contractor_management)
    id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
    id_role = db.Column(db.Integer, db.ForeignKey('roles.id'))

    def __init__(self, name, middle_name, last_name, address, role, date_start, source, id_city, id_role):
        self.name = name
        self.middle_name = middle_name
        self.last_name = last_name
        self.address = address
        self.date_start = date_start
        self.source = source
        self.id_city = id_city
        self.id_role = id_role


class Governement(db.Model):
    __tablename__ = "governement"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(50), nullable=False)
    middle_name = db.Column(db.String(100), nullable=True)
    last_name = db.Column(db.String(50), nullable=False)
    address = db.Column(db.String(255), nullable=True)
    date = db.Column(db.Date(), nullable=False)
    politic_part = db.Column(db.String(255), nullable=False)
    source = db.Column(db.String(255), nullable=False)
    id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
    id_role = db.Column(db.Integer, db.ForeignKey('roles.id'))

    def __init__(self, name, middle_name, last_name, address, role, date, source, id_city, id_role):
        self.name = name
        self.middle_name = middle_name
        self.last_name = last_name
        self.address = address
        self.date = date
        self.source = source
        self.id_city = id_city
        self.id_role = id_role


@app.route("/")
def hello_world():
    return jsonify(hello="world")


@app.route("/static/<path:filename>")
def staticfiles(filename):
    return send_from_directory(app.config["STATIC_FOLDER"], filename)


@app.route("/media/<path:filename>")
def mediafiles(filename):
    return send_from_directory(app.config["MEDIA_FOLDER"], filename)


@app.route("/upload", methods=["GET", "POST"])
def upload_file():
    if request.method == "POST":
        file = request.files["file"]
        filename = secure_filename(file.filename)
        file.save(os.path.join(app.config["MEDIA_FOLDER"], filename))
    return f"""{'''
    <!doctype html>
    <title>upload new File</title>
    <h1>Upload new File</h1>
    <form action="" method=post enctype=multipart/form-data>
      <p><input type=file name=file><input type=submit value=Upload>
    </form>'''}
    """
