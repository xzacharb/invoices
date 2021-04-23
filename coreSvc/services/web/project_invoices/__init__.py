from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config.from_object("project_invoices.config.Config")
db = SQLAlchemy(app)

from .views import views

app.register_blueprint(views, url_prefix='/')

# import os

# from werkzeug.utils import secure_filename
# from flask import (
#     Flask,
#     jsonify,
#     send_from_directory,
#     request,
#     redirect,
#     url_for,
#     make_response
# )
# from flask_sqlalchemy import SQLAlchemy
# # from sqlalchemy.sql import func
# from marshmallow import fields
# from marshmallow_sqlalchemy import ModelSchema


# app = Flask(__name__)
# app.config.from_object("project_invoices.config.Config")
# db = SQLAlchemy(app)


# class Legal_forms(db.Model):
#     __tablename__ = "legal_forms"

#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String(255), nullable=False)
#     short = db.Column(db.String(255), nullable=False)
#     contractor = db.relationship("Contractor", backref=db.backref("legal_forms", uselist=False))

#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, name, short):
#         self.name = name
#         self.short = short

#     def __repr__(self):
#         return f"{self.id}"


# class Cities(db.Model):
#     __tablename__ = "cities"

#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String(255),unique=True, nullable=False)
#     contractors = db.relationship("Contractor", backref=db.backref("cities"))
#     invoices = db.relationship("Invoices", backref=db.backref("cities"))
#     comp_manag = db.relationship("Company_management", backref=db.backref("cities"))
#     government = db.relationship("Government", backref=db.backref("cities"))

#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, name):
#         self.name = name

#     def __repr__(self):
#         return f"{self.id}"


# class Contractor(db.Model):
#     __tablename__ = "contractor"

#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String(255),unique=True, nullable=False)
#     address = db.Column(db.String(255), nullable=False)
#     description = db.Column(db.String(), nullable=False)
#     # date_created = db.Column(db.DateTime(timezone=True), server_default=func.now())
#     date_created = db.Column(db.Date(), nullable=False)
#     source = db.Column(db.String(255), nullable=False)
#     ico = db.Column(db.String(255), nullable=True)
#     id_leg_form = db.Column(db.Integer, db.ForeignKey('legal_forms.id'))
#     id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
#     invoices = db.relationship("Invoices", backref=db.backref("contractor"))
#     management = db.relationship("Company_management", backref=db.backref("contractor"))
    

#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, name, address, description, date_created, source, ico, id_leg_form, id_city):
#         self.name = name
#         self.address = address
#         self.description = description
#         self.date_created = date_created
#         self.source = source
#         self.ico = ico
#         self.id_leg_form = id_leg_form
#         self.id_city = id_city


#     def __repr__(self):
#         return f"{self.id}"


# class Invoices(db.Model):
#     __tablename__ = "invoices"

#     id = db.Column(db.Integer, primary_key=True)
#     subject = db.Column(db.String(255), nullable=False)
#     description = db.Column(db.String(), nullable=False)
#     price = db.Column(db.Integer, nullable=False)
#     date1 = db.Column(db.Date(), nullable=False)
#     date2 = db.Column(db.Date(), nullable=False)
#     date3 = db.Column(db.Date(), nullable=False)
#     source = db.Column(db.String(255), nullable=False)
#     id_contractor = db.Column(db.Integer, db.ForeignKey('contractor.id'))
#     id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
#     evaluated = db.relationship("Evaluated", backref=db.backref("invoices", uselist=False))

#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, subject, description, price, date1, date2, date3, source, id_contractor, id_city):
#         self.subject = subject
#         self.description = description
#         self.price = price
#         self.date1 = date1
#         self.date2 = date2
#         self.date3 = date3
#         self.source = source
#         self.id_contractor = id_contractor
#         self.id_city = id_city
    
#     def __repr__(self):
#         return f"{self.id}"


# class Evaluated(db.Model):
#     __tablename__ = "evaluated"

#     id = db.Column(db.Integer, primary_key=True)
#     value = db.Column(db.String(255), nullable=False)
#     description = db.Column(db.String(), nullable=False)
#     evaluation = db.Column(db.String(255), nullable=True)
#     id_invoice = db.Column(db.Integer, db.ForeignKey('invoices.id'))

#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, value, description, evaluation, id_invoice):
#         self.value = value
#         self.description = description
#         self.evaluation = evaluation
#         self.id_invoice = id_invoice

#     def __repr__(self):
#         return f"{self.id}"


# class Roles(db.Model):
#     __tablename__ = "roles"

#     id = db.Column(db.Integer, primary_key=True)
#     role_name = db.Column(db.String(255), nullable=False)
#     comp_manag = db.relationship("Company_management", backref=db.backref("roles"))
#     governement = db.relationship("Government", backref=db.backref("roles"))

#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, role_name):
#         self.role_name = role_name

#     def __repr__(self):
#         return f"{self.id}"


# class Company_management(db.Model):
#     __tablename__ = "company_management"

#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String(50), nullable=False)
#     middle_name = db.Column(db.String(100), nullable=True)
#     last_name = db.Column(db.String(50), nullable=False)
#     address = db.Column(db.String(255), nullable=True)
#     date_start = db.Column(db.Date(), nullable=False)
#     source = db.Column(db.String(255), nullable=False)
#     id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
#     id_role = db.Column(db.Integer, db.ForeignKey('roles.id'))
#     id_contractor = db.Column(db.Integer, db.ForeignKey('contractor.id'))


#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, name, middle_name, last_name, address, date_start, source, id_city, id_role, id_contractor):
#         self.name = name
#         self.middle_name = middle_name
#         self.last_name = last_name
#         self.address = address
#         self.date_start = date_start
#         self.source = source
#         self.id_city = id_city
#         self.id_role = id_role
#         self.id_contractor = id_contractor


#     def __repr__(self):
#         return f"{self.id}"


# class Government(db.Model):
#     __tablename__ = "government"

#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String(50), nullable=False)
#     middle_name = db.Column(db.String(100), nullable=True)
#     last_name = db.Column(db.String(50), nullable=False)
#     address = db.Column(db.String(255), nullable=True)
#     date = db.Column(db.Date(), nullable=False)
#     politic_part = db.Column(db.String(255), nullable=False)
#     source = db.Column(db.String(255), nullable=False)
#     id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
#     id_role = db.Column(db.Integer, db.ForeignKey('roles.id'))

#     def create(self):
#         db.session.add(self)
#         db.session.commit()
#         return self

#     def __init__(self, name, middle_name, last_name, address, date, politic_part, source, id_city, id_role):
#         self.name = name
#         self.middle_name = middle_name
#         self.last_name = last_name
#         self.address = address
#         self.date = date
#         self.politic_part = politic_part
#         self.source = source
#         self.id_city = id_city
#         self.id_role = id_role

#     def __repr__(self):
#         return f"{self.id}"


# db.create_all()


# class Legal_formsSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Legal_forms
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     name = fields.String(required=True)
#     short = fields.String(required=True)


# class CitiesSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Cities
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     name = fields.String(required=True)


# class ContractorSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Contractor
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     name = fields.String(required=True)
#     address = fields.String(required=True)
#     description = fields.String(required=True)
#     date_created = fields.Date(required=True)
#     source = fields.String(required=True)
#     ico = fields.String(required=True)
#     id_leg_form = fields.Integer(required=True)
#     id_city = fields.Integer(required=True)


# class InvoicesSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Invoices
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     subject = fields.String(required=True)
#     description = fields.String(required=True)
#     price = fields.Integer(required=True)
#     date1 = fields.Date(required=True)
#     date2 = fields.Date(required=True)
#     date3 = fields.Date(required=True)
#     source = fields.String(required=True)
#     id_contractor = fields.Integer(required=True)
#     id_city = fields.Integer(required=True)



# class EvaluatedSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Evaluated
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     value = fields.String(required=True)
#     description = fields.String(required=True)
#     evaluation = fields.String(required=True)
#     id_invoice = fields.Integer(required=True)


# class RolesSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Roles
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     role_name = fields.String(required=True)


# class Company_managementSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Company_management
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     name = fields.String(required=True)
#     middle_name = fields.String(required=True)
#     last_name = fields.String(required=True)
#     address = fields.String(required=True)
#     date_start = fields.Date(required=True)
#     source = fields.String(required=True)
#     id_city = fields.Integer(required=True)
#     id_role = fields.Integer(required=True)
#     id_contractor = fields.Integer(required=True)


# class GovernmentSchema(ModelSchema):
#     class Meta(ModelSchema.Meta):
#         model = Government
#         sqla_session = db.session
#     id  = fields.Number(dump_only=True)
#     name = fields.String(required=True)
#     middle_name = fields.String(required=True)
#     last_name = fields.String(required=True)
#     address = fields.String(required=True)
#     date = fields.Date(required=True)
#     politic_part = fields.String(required=True)
#     source = fields.String(required=True)
#     id_city = fields.Integer(required=True)
#     id_role = fields.Integer(required=True)


# @app.route("/forms", methods=['POST'])
# def create_forms():
#     data = request.get_json()
#     forms_schema = Legal_formsSchema()
#     form = forms_schema.load(data)
#     result = forms_schema.dump(form.create())
#     return make_response(jsonify({"form": result}), 200)


# @app.route("/forms", methods=["GET"])
# def index_form():
#     get_forms = Legal_forms.query.all()
#     forms_schema = Legal_formsSchema(many=True)
#     forms = forms_schema.dump(get_forms)
#     return make_response(jsonify({"forms": forms}))


# @app.route("/forms/<id>", methods=["GET"])
# def get_form_by_id(id):
#     get_form = Legal_forms.query.get(id)
#     forms_schema = Legal_formsSchema()
#     form = forms_schema.dump(get_form)
#     return make_response(jsonify({"form": form}))


# @app.route('/forms/<id>', methods=['PUT'])
# def update_form_by_id(id):
#    data = request.get_json()
#    get_form = Legal_forms.query.get(id)
#    if data.get('name'):
#        get_form.name = data['name']
#    if data.get('short'):
#        get_form.short = data['short']
#    db.session.add(get_form)
#    db.session.commit()
#    forms_schema = Legal_formsSchema(only=['id', 'name', 'short'])
#    form = forms_schema.dump(get_form)

#    return make_response(jsonify({"form": form}))


# @app.route('/forms/<id>', methods=['DELETE'])
# def delete_form_by_id(id):
#    get_form = Legal_forms.query.get(id)
#    db.session.delete(get_form)
#    db.session.commit()
#    return make_response("", 204)


# @app.route("/cities", methods=['POST'])
# def create_city():
#     data = request.get_json()
#     city_schema = CitiesSchema()
#     city = city_schema.load(data)
#     result = city_schema.dump(city.create())

#     return make_response(jsonify({"city": result}), 200)


# @app.route("/cities", methods=["GET"])
# def index_city():
#     get_cities = Cities.query.all()
#     city_schema = CitiesSchema(many=True)
#     cities = city_schema.dump(get_cities)
#     return make_response(jsonify({"cities": cities}))


# @app.route("/cities/<id>", methods=["GET"])
# def get_city_by_id(id):
#     get_city = Cities.query.get(id)
#     city_schema = CitiesSchema()
#     city = city_schema.dump(get_city)
#     return make_response(jsonify({"city": city}))


# @app.route('/cities/<id>', methods=['PUT'])
# def update_city_by_id(id):
#    data = request.get_json()
#    get_city = Cities.query.get(id)
#    if data.get('name'):
#        get_city.name = data['name']

#    db.session.add(get_city)
#    db.session.commit()
#    city_schema = CitiesSchema(only=['id', 'name'])
#    city = city_schema.dump(get_city)

#    return make_response(jsonify({"city": city}))


# @app.route('/cities/<id>', methods=['DELETE'])
# def delete_city_by_id(id):
#    get_city = Cities.query.get(id)
#    db.session.delete(get_city)
#    db.session.commit()
#    return make_response("", 204)


# @app.route("/contractor", methods=['POST'])
# def create_contractor():
#     data = request.get_json()
#     contractor_schema = ContractorSchema()
#     contractor = contractor_schema.load(data)
#     result = contractor_schema.dump(contractor.create())

#     return make_response(jsonify({"contractor": result}), 200)


# @app.route("/contractor", methods=["GET"])
# def index_contractor():
#     get_contractors = Contractor.query.all()
#     contractor_schema = ContractorSchema(many=True)
#     contractors = contractor_schema.dump(get_contractors)
#     return make_response(jsonify({"contractors": contractors}))


# @app.route("/contractor/<id>", methods=["GET"])
# def get_contractor_by_id(id):
#     get_contractor = Contractor.query.get(id)
#     contractor_schema = ContractorSchema()
#     contractor = contractor_schema.dump(get_contractor)
#     return make_response(jsonify({"contractor": contractor}))


# @app.route('/contractor/<id>', methods=['PUT'])
# def update_contractor_by_id(id):
#    data = request.get_json()
#    get_contractor = Contractor.query.get(id)
#    if data.get('name'):
#        get_contractor.name = data['name']
#    if data.get('address'):
#        get_contractor.address = data['address']
#    if data.get('description'):
#        get_contractor.description = data['description']
#    if data.get('date_created'):
#        get_contractor.date_created = data['date_created']
#    if data.get('source'):
#        get_contractor.source = data['source']
#    if data.get('ico'):
#        get_contractor.ico = data['ico']
#    if data.get('id_leg_form'):
#        get_contractor.id_leg_form = data['id_leg_form']
#    if data.get('id_city'):
#        get_contractor.id_city = data['id_city']
#    db.session.add(get_contractor)
#    db.session.commit()
#    contractor_schema = ContractorSchema(only=['id', 'name', 'address', 'description', 'date_created', 'source', 'ico', 'id_leg_form', 'id_city'])
#    contractor = contractor_schema.dump(get_contractor)

#    return make_response(jsonify({"contractor": contractor}))


# @app.route('/contractor/<id>', methods=['DELETE'])
# def delete_contractor_by_id(id):
#    get_contractor = Contractor.query.get(id)
#    db.session.delete(get_contractor)
#    db.session.commit()
#    return make_response("", 204)


# @app.route("/invoices", methods=['POST'])
# def create_invoice():
#     data = request.get_json()
#     invoices_schema = InvoicesSchema()
#     invoice = invoices_schema.load(data)
#     result = invoices_schema.dump(invoice.create())
#     return make_response(jsonify({"invoice": result}), 200)


# @app.route("/invoices", methods=["GET"])
# def index_invoice():
#     get_invoices = Invoices.query.all()
#     invoices_schema = InvoicesSchema(many=True)
#     invoices = invoices_schema.dump(get_invoices)
#     return make_response(jsonify({"invoices": invoices}))


# @app.route("/invoices/<id>", methods=["GET"])
# def get_invoice_by_id(id):
#     get_invoice = Invoices.query.get(id)
#     invoices_schema = InvoicesSchema()
#     invoice = invoices_schema.dump(get_invoice)
#     return make_response(jsonify({"invoice": invoice}))


# @app.route('/invoices/<id>', methods=['PUT'])
# def update_invoice_by_id(id):
#    data = request.get_json()
#    get_invoice = Invoices.query.get(id)
#    if data.get('subject'):
#        get_invoice.subject = data['subject']
#    if data.get('description'):
#        get_invoice.description = data['description']
#    if data.get('price'):
#        get_invoice.price = data['price']
#    if data.get('date1'):
#        get_invoice.date1 = data['date1']
#    if data.get('date2'):
#        get_invoice.date2 = data['date2']
#    if data.get('date3'):
#        get_invoice.date3 = data['date3']
#    if data.get('source'):
#        get_invoice.source = data['source']
#    if data.get('id_contractor'):
#        get_invoice.id_contractor = data['id_contractor']
#    if data.get('id_city'):
#        get_invoice.id_city = data['id_city']
#    db.session.add(get_invoice)
#    db.session.commit()
#    invoices_schema = InvoicesSchema(only=['id', 'subject', 'description', 'price', 'date1', 'date2', 'date3', 'source', 'id_contractor', 'id_city'])
#    invoice = invoices_schema.dump(get_invoice)

#    return make_response(jsonify({"invoice": invoice}))


# @app.route('/invoices/<id>', methods=['DELETE'])
# def delete_invoice_by_id(id):
#    get_invoice = Invoices.query.get(id)
#    db.session.delete(get_invoice)
#    db.session.commit()
#    return make_response("", 204)


# @app.route("/evaluated", methods=['POST'])
# def create_evaluated():
#     data = request.get_json()
#     evaluated_schema = EvaluatedSchema()
#     evaluated = evaluated_schema.load(data)
#     result = evaluated_schema.dump(evaluated.create())
#     return make_response(jsonify({"evaluated": result}), 200)


# @app.route("/evaluated", methods=["GET"])
# def index_evaluated():
#     get_evaluated = Evaluated.query.all()
#     evaluated_schema = EvaluatedSchema(many=True)
#     evaluated = evaluated_schema.dump(get_evaluated)
#     return make_response(jsonify({"evaluated": evaluated}))


# @app.route("/evaluated/<id>", methods=["GET"])
# def get_evaluated_by_id(id):
#     get_evaluated = Evaluated.query.get(id)
#     evaluated_schema = EvaluatedSchema()
#     evaluated = evaluated_schema.dump(get_evaluated)
#     return make_response(jsonify({"evaluated": evaluated}))


# @app.route('/evaluated/<id>', methods=['PUT'])
# def update_evaluated_by_id(id):
#    data = request.get_json()
#    get_evaluated = Evaluated.query.get(id)
#    if data.get('value'):
#        get_evaluated.value = data['value']
#    if data.get('description'):
#        get_evaluated.description = data['description']
#    if data.get('evaluation'):
#        get_evaluated.evaluation = data['evaluation']
#    if data.get('id_invoice'):
#        get_evaluated.id_invoice = data['id_invoice']
#    db.session.add(get_evaluated)
#    db.session.commit()
#    evaluated_schema = EvaluatedSchema(only=['id', 'value', 'description', 'evaluation', 'id_invoice'])
#    evaluated = evaluated_schema.dump(get_evaluated)

#    return make_response(jsonify({"evaluated": evaluated}))


# @app.route('/evaluated/<id>', methods=['DELETE'])
# def delete_evaluated_by_id(id):
#    get_evaluated = Evaluated.query.get(id)
#    db.session.delete(get_evaluated)
#    db.session.commit()
#    return make_response("", 204)


# @app.route("/roles", methods=['POST'])
# def create_role():
#     data = request.get_json()
#     role_schema = RolesSchema()
#     role = role_schema.load(data)
#     result = role_schema.dump(role.create())
#     return make_response(jsonify({"role": result}), 200)


# @app.route("/roles", methods=["GET"])
# def index_role():
#     get_roles = Roles.query.all()
#     role_schema = RolesSchema(many=True)
#     roles = role_schema.dump(get_roles)
#     return make_response(jsonify({"roles": roles}))


# @app.route("/roles/<id>", methods=["GET"])
# def get_role_by_id(id):
#     get_role = Roles.query.get(id)
#     role_schema = RolesSchema()
#     role = role_schema.dump(get_role)
#     return make_response(jsonify({"role": role}))


# @app.route('/roles/<id>', methods=['PUT'])
# def update_role_by_id(id):
#    data = request.get_json()
#    get_role = Roles.query.get(id)
#    if data.get('role_name'):
#        get_role.name = data['role_name']
#    db.session.add(get_role)
#    db.session.commit()
#    role_schema = RolesSchema(only=['id', 'role_name'])
#    role = role_schema.dump(get_role)

#    return make_response(jsonify({"role": role}))


# @app.route('/roles/<id>', methods=['DELETE'])
# def delete_role_by_id(id):
#    get_role = Roles.query.get(id)
#    db.session.delete(get_role)
#    db.session.commit()
#    return make_response("", 204)


# @app.route("/management", methods=['POST'])
# def create_management():
#     data = request.get_json()
#     management_schema = Company_managementSchema()
#     management = management_schema.load(data)
#     result = management_schema.dump(management.create())
#     return make_response(jsonify({"management": result}), 200)


# @app.route("/management", methods=["GET"])
# def index_management():
#     get_managements = Company_management.query.all()
#     management_schema = Company_managementSchema(many=True)
#     managements = management_schema.dump(get_managements)
#     return make_response(jsonify({"managements": managements}))


# @app.route("/management/<id>", methods=["GET"])
# def get_management_by_id(id):
#     get_management = Company_management.query.get(id)
#     management_schema = Company_managementSchema()
#     management = management_schema.dump(get_management)
#     return make_response(jsonify({"management": management}))


# @app.route('/management/<id>', methods=['PUT'])
# def update_management_by_id(id):
#    data = request.get_json()
#    get_management = Company_management.query.get(id)
#    if data.get('name'):
#        get_management.name = data['name']
#    if data.get('middle_name'):
#        get_management.middle_name = data['middle_name']
#    if data.get('last_name'):
#        get_management.last_name = data['last_name']
#    if data.get('address'):
#        get_management.address = data['address']
#    if data.get('date_start'):
#        get_management.date_start = data['date_start']
#    if data.get('source'):
#        get_management.source = data['source']
#    if data.get('id_city'):
#        get_management.id_city = data['id_city']
#    if data.get('id_role'):
#        get_management.id_role = data['id_role']
#    if data.get('id_contractor'):
#        get_management.id_contractor = data['id_contractor']
#    db.session.add(get_management)
#    db.session.commit()
#    management_schema = Company_managementSchema(only=['id', 'name', 'middle_name', 'last_name', 'address', 'date_start', 'source', 'id_city', 'id_role', 'id_contractor'])
#    management = management_schema.dump(get_management)

#    return make_response(jsonify({"management": management}))


# @app.route('/management/<id>', methods=['DELETE'])
# def delete_management_by_id(id):
#    get_management = Company_management.query.get(id)
#    db.session.delete(get_management)
#    db.session.commit()
#    return make_response("", 204)


# @app.route("/government", methods=['POST'])
# def create_government():
#     data = request.get_json()
#     government_schema = GovernmentSchema()
#     government = government_schema.load(data)
#     result = government_schema.dump(government.create())
#     return make_response(jsonify({"government": result}), 200)


# @app.route("/government", methods=["GET"])
# def index_government():
#     get_government = Government.query.all()
#     government_schema = GovernmentSchema(many=True)
#     governments = government_schema.dump(get_government)
#     return make_response(jsonify({"governments": governments}))


# @app.route("/government/<id>", methods=["GET"])
# def get_government_by_id(id):
#     get_government = Government.query.get(id)
#     government_schema = GovernmentSchema()
#     government = governement_schema.dump(get_government)
#     return make_response(jsonify({"government": government}))


# @app.route('/government/<id>', methods=['PUT'])
# def update_government_by_id(id):
#    data = request.get_json()
#    get_government = Government.query.get(id)
#    if data.get('name'):
#        get_government.name = data['name']
#    if data.get('middle_name'):
#        get_government.middle_name = data['middle_name']
#    if data.get('last_name'):
#        get_government.last_name = data['last_name']
#    if data.get('address'):
#        get_government.address = data['address']
#    if data.get('date'):
#        get_government.date = data['date']
#    if data.get('politic_part'):
#        get_government.politic_part = data['politic_part']
#    if data.get('source'):
#        get_government.source = data['source']
#    if data.get('id_city'):
#        get_government.id_city = data['id_city']
#    if data.get('id_role'):
#        get_government.id_role = data['id_role']
#    db.session.add(get_government)
#    db.session.commit()
#    government_schema = GovernmentSchema(only=['id', 'name', 'middle_name', 'last_name', 'address', 'date', 'politic_part', 'source', 'id_city', 'id_role'])
#    government = government_schema.dump(get_government)

#    return make_response(jsonify({"government": government}))


# @app.route('/government/<id>', methods=['DELETE'])
# def delete_government_by_id(id):
#    get_government = Government.query.get(id)
#    db.session.delete(get_government)
#    db.session.commit()
#    return make_response("", 204)

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

