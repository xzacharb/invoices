import os
from werkzeug.utils import secure_filename
from flask import (
    Blueprint,
    Flask,
    jsonify,
    send_from_directory,
    request,
    redirect,
    url_for,
    make_response
)
from . import db
from .models import Legal_forms, Contractor, Cities, Invoices, Evaluated, Roles, Company_management, Government
from .schema import Legal_formsSchema, ContractorSchema, CitiesSchema, InvoicesSchema, EvaluatedSchema, RolesSchema, Company_managementSchema, GovernmentSchema

views = Blueprint('views', __name__)


@views.route("/forms", methods=['POST'])
def create_forms():
    data = request.get_json()
    forms_schema = Legal_formsSchema()
    form = forms_schema.load(data)
    result = forms_schema.dump(form.create())
    return make_response(jsonify({"form": result}), 200)


@views.route("/forms", methods=["GET"])
def index_form():
    get_forms = Legal_forms.query.all()
    forms_schema = Legal_formsSchema(many=True)
    forms = forms_schema.dump(get_forms)
    return make_response(jsonify({"forms": forms}))


@views.route("/forms/<id>", methods=["GET"])
def get_form_by_id(id):
    get_form = Legal_forms.query.get(id)
    forms_schema = Legal_formsSchema()
    form = forms_schema.dump(get_form)
    return make_response(jsonify({"form": form}))


@views.route('/forms/<id>', methods=['PUT'])
def update_form_by_id(id):
    data = request.get_json()
    get_form = Legal_forms.query.get(id)
    if data.get('name'):
        get_form.name = data['name']
    if data.get('short'):
        get_form.short = data['short']
    db.session.add(get_form)
    db.session.commit()
    forms_schema = Legal_formsSchema(only=['id', 'name', 'short'])
    form = forms_schema.dump(get_form)

    return make_response(jsonify({"form": form}))


@views.route('/forms/<id>', methods=['DELETE'])
def delete_form_by_id(id):
    get_form = Legal_forms.query.get(id)
    db.session.delete(get_form)
    db.session.commit()
    return make_response("", 204)


@views.route("/cities", methods=['POST'])
def create_city():
    data = request.get_json()
    city_schema = CitiesSchema()
    city = city_schema.load(data)
    result = city_schema.dump(city.create())

    return make_response(jsonify({"city": result}), 200)


@views.route("/cities", methods=["GET"])
def index_city():
    get_cities = Cities.query.all()
    city_schema = CitiesSchema(many=True)
    cities = city_schema.dump(get_cities)
    return make_response(jsonify({"cities": cities}))


@views.route("/cities/<id>", methods=["GET"])
def get_city_by_id(id):
    get_city = Cities.query.get(id)
    city_schema = CitiesSchema()
    city = city_schema.dump(get_city)
    return make_response(jsonify({"city": city}))


@views.route('/cities/<id>', methods=['PUT'])
def update_city_by_id(id):
    data = request.get_json()
    get_city = Cities.query.get(id)
    if data.get('name'):
        get_city.name = data['name']

    db.session.add(get_city)
    db.session.commit()
    city_schema = CitiesSchema(only=['id', 'name'])
    city = city_schema.dump(get_city)

    return make_response(jsonify({"city": city}))


@views.route('/cities/<id>', methods=['DELETE'])
def delete_city_by_id(id):
    get_city = Cities.query.get(id)
    db.session.delete(get_city)
    db.session.commit()
    return make_response("", 204)


@views.route("/contractor", methods=['POST'])
def create_contractor():
    data = request.get_json()
    contractor_schema = ContractorSchema()
    contractor = contractor_schema.load(data)
    result = contractor_schema.dump(contractor.create())

    return make_response(jsonify({"contractor": result}), 200)


@views.route("/contractor", methods=["GET"])
def index_contractor():
    get_contractors = Contractor.query.all()
    contractor_schema = ContractorSchema(many=True)
    contractors = contractor_schema.dump(get_contractors)
    return make_response(jsonify({"contractors": contractors}))


@views.route("/contractor/<id>", methods=["GET"])
def get_contractor_by_id(id):
    get_contractor = Contractor.query.get(id)
    contractor_schema = ContractorSchema()
    contractor = contractor_schema.dump(get_contractor)
    return make_response(jsonify({"contractor": contractor}))


@views.route('/contractor/<id>', methods=['PUT'])
def update_contractor_by_id(id):
    data = request.get_json()
    get_contractor = Contractor.query.get(id)
    if data.get('name'):
        get_contractor.name = data['name']
    if data.get('address'):
        get_contractor.address = data['address']
    if data.get('description'):
        get_contractor.description = data['description']
    if data.get('date_created'):
        get_contractor.date_created = data['date_created']
    if data.get('source'):
        get_contractor.source = data['source']
    if data.get('ico'):
        get_contractor.ico = data['ico']
    if data.get('id_leg_form'):
        get_contractor.id_leg_form = data['id_leg_form']
    if data.get('id_city'):
        get_contractor.id_city = data['id_city']
    db.session.add(get_contractor)
    db.session.commit()
    contractor_schema = ContractorSchema(only=['id', 'name', 'address', 'description', 'date_created', 'source', 'ico', 'id_leg_form', 'id_city'])
    contractor = contractor_schema.dump(get_contractor)

    return make_response(jsonify({"contractor": contractor}))


@views.route('/contractor/<id>', methods=['DELETE'])
def delete_contractor_by_id(id):
    get_contractor = Contractor.query.get(id)
    db.session.delete(get_contractor)
    db.session.commit()
    return make_response("", 204)


@views.route("/invoices", methods=['POST'])
def create_invoice():
    data = request.get_json()
    invoices_schema = InvoicesSchema()
    invoice = invoices_schema.load(data)
    result = invoices_schema.dump(invoice.create())
    return make_response(jsonify({"invoice": result}), 200)


@views.route("/invoices", methods=["GET"])
def index_invoice():
    get_invoices = Invoices.query.all()
    invoices_schema = InvoicesSchema(many=True)
    invoices = invoices_schema.dump(get_invoices)
    return make_response(jsonify({"invoices": invoices}))


@views.route("/invoices/<id>", methods=["GET"])
def get_invoice_by_id(id):
    get_invoice = Invoices.query.get(id)
    invoices_schema = InvoicesSchema()
    invoice = invoices_schema.dump(get_invoice)
    return make_response(jsonify({"invoice": invoice}))


@views.route('/invoices/<id>', methods=['PUT'])
def update_invoice_by_id(id):
    data = request.get_json()
    get_invoice = Invoices.query.get(id)
    if data.get('subject'):
        get_invoice.subject = data['subject']
    if data.get('description'):
        get_invoice.description = data['description']
    if data.get('price'):
        get_invoice.price = data['price']
    if data.get('date1'):
        get_invoice.date1 = data['date1']
    if data.get('date2'):
        get_invoice.date2 = data['date2']
    if data.get('date3'):
        get_invoice.date3 = data['date3']
    if data.get('source'):
        get_invoice.source = data['source']
    if data.get('id_contractor'):
        get_invoice.id_contractor = data['id_contractor']
    if data.get('id_city'):
        get_invoice.id_city = data['id_city']
    db.session.add(get_invoice)
    db.session.commit()
    invoices_schema = InvoicesSchema(only=['id', 'subject', 'description', 'price', 'date1', 'date2', 'date3', 'source', 'id_contractor', 'id_city'])
    invoice = invoices_schema.dump(get_invoice)

    return make_response(jsonify({"invoice": invoice}))


@views.route('/invoices/<id>', methods=['DELETE'])
def delete_invoice_by_id(id):
    get_invoice = Invoices.query.get(id)
    db.session.delete(get_invoice)
    db.session.commit()
    return make_response("", 204)


@views.route("/evaluated", methods=['POST'])
def create_evaluated():
    data = request.get_json()
    evaluated_schema = EvaluatedSchema()
    evaluated = evaluated_schema.load(data)
    result = evaluated_schema.dump(evaluated.create())
    return make_response(jsonify({"evaluated": result}), 200)


@views.route("/evaluated", methods=["GET"])
def index_evaluated():
    get_evaluated = Evaluated.query.all()
    evaluated_schema = EvaluatedSchema(many=True)
    evaluated = evaluated_schema.dump(get_evaluated)
    return make_response(jsonify({"evaluated": evaluated}))


@views.route("/evaluated/<id>", methods=["GET"])
def get_evaluated_by_id(id):
    get_evaluated = Evaluated.query.get(id)
    evaluated_schema = EvaluatedSchema()
    evaluated = evaluated_schema.dump(get_evaluated)
    return make_response(jsonify({"evaluated": evaluated}))


@views.route('/evaluated/<id>', methods=['PUT'])
def update_evaluated_by_id(id):
    data = request.get_json()
    get_evaluated = Evaluated.query.get(id)
    if data.get('value'):
        get_evaluated.value = data['value']
    if data.get('description'):
        get_evaluated.description = data['description']
    if data.get('evaluation'):
        get_evaluated.evaluation = data['evaluation']
    if data.get('id_invoice'):
        get_evaluated.id_invoice = data['id_invoice']
    db.session.add(get_evaluated)
    db.session.commit()
    evaluated_schema = EvaluatedSchema(only=['id', 'value', 'description', 'evaluation', 'id_invoice'])
    evaluated = evaluated_schema.dump(get_evaluated)

    return make_response(jsonify({"evaluated": evaluated}))


@views.route('/evaluated/<id>', methods=['DELETE'])
def delete_evaluated_by_id(id):
    get_evaluated = Evaluated.query.get(id)
    db.session.delete(get_evaluated)
    db.session.commit()
    return make_response("", 204)


@views.route("/roles", methods=['POST'])
def create_role():
    data = request.get_json()
    role_schema = RolesSchema()
    role = role_schema.load(data)
    result = role_schema.dump(role.create())
    return make_response(jsonify({"role": result}), 200)


@views.route("/roles", methods=["GET"])
def index_role():
    get_roles = Roles.query.all()
    role_schema = RolesSchema(many=True)
    roles = role_schema.dump(get_roles)
    return make_response(jsonify({"roles": roles}))


@views.route("/roles/<id>", methods=["GET"])
def get_role_by_id(id):
    get_role = Roles.query.get(id)
    role_schema = RolesSchema()
    role = role_schema.dump(get_role)
    return make_response(jsonify({"role": role}))


@views.route('/roles/<id>', methods=['PUT'])
def update_role_by_id(id):
    data = request.get_json()
    get_role = Roles.query.get(id)
    if data.get('role_name'):
        get_role.name = data['role_name']
    db.session.add(get_role)
    db.session.commit()
    role_schema = RolesSchema(only=['id', 'role_name'])
    role = role_schema.dump(get_role)

    return make_response(jsonify({"role": role}))


@views.route('/roles/<id>', methods=['DELETE'])
def delete_role_by_id(id):
    get_role = Roles.query.get(id)
    db.session.delete(get_role)
    db.session.commit()
    return make_response("", 204)


@views.route("/management", methods=['POST'])
def create_management():
    data = request.get_json()
    management_schema = Company_managementSchema()
    management = management_schema.load(data)
    result = management_schema.dump(management.create())
    return make_response(jsonify({"management": result}), 200)


@views.route("/management", methods=["GET"])
def index_management():
    get_managements = Company_management.query.all()
    management_schema = Company_managementSchema(many=True)
    managements = management_schema.dump(get_managements)
    return make_response(jsonify({"managements": managements}))


@views.route("/management/<id>", methods=["GET"])
def get_management_by_id(id):
    get_management = Company_management.query.get(id)
    management_schema = Company_managementSchema()
    management = management_schema.dump(get_management)
    return make_response(jsonify({"management": management}))


@views.route('/management/<id>', methods=['PUT'])
def update_management_by_id(id):
    data = request.get_json()
    get_management = Company_management.query.get(id)
    if data.get('name'):
        get_management.name = data['name']
    if data.get('middle_name'):
        get_management.middle_name = data['middle_name']
    if data.get('last_name'):
        get_management.last_name = data['last_name']
    if data.get('address'):
        get_management.address = data['address']
    if data.get('date_start'):
        get_management.date_start = data['date_start']
    if data.get('source'):
        get_management.source = data['source']
    if data.get('id_city'):
        get_management.id_city = data['id_city']
    if data.get('id_role'):
        get_management.id_role = data['id_role']
    if data.get('id_contractor'):
        get_management.id_contractor = data['id_contractor']
    db.session.add(get_management)
    db.session.commit()
    management_schema = Company_managementSchema(only=['id', 'name', 'middle_name', 'last_name', 'address', 'date_start', 'source', 'id_city', 'id_role', 'id_contractor'])
    management = management_schema.dump(get_management)

    return make_response(jsonify({"management": management}))


@views.route('/management/<id>', methods=['DELETE'])
def delete_management_by_id(id):
    get_management = Company_management.query.get(id)
    db.session.delete(get_management)
    db.session.commit()
    return make_response("", 204)


@views.route("/government", methods=['POST'])
def create_government():
    data = request.get_json()
    government_schema = GovernmentSchema()
    government = government_schema.load(data)
    result = government_schema.dump(government.create())
    return make_response(jsonify({"government": result}), 200)


@views.route("/government", methods=["GET"])
def index_government():
    get_government = Government.query.all()
    government_schema = GovernmentSchema(many=True)
    governments = government_schema.dump(get_government)
    return make_response(jsonify({"governments": governments}))


@views.route("/government/<id>", methods=["GET"])
def get_government_by_id(id):
    get_government = Government.query.get(id)
    government_schema = GovernmentSchema()
    government = government_schema.dump(get_government)
    return make_response(jsonify({"government": government}))


@views.route('/government/<id>', methods=['PUT'])
def update_government_by_id(id):
    data = request.get_json()
    get_government = Government.query.get(id)
    if data.get('name'):
        get_government.name = data['name']
    if data.get('middle_name'):
        get_government.middle_name = data['middle_name']
    if data.get('last_name'):
        get_government.last_name = data['last_name']
    if data.get('address'):
        get_government.address = data['address']
    if data.get('date'):
        get_government.date = data['date']
    if data.get('politic_part'):
        get_government.politic_part = data['politic_part']
    if data.get('source'):
        get_government.source = data['source']
    if data.get('id_city'):
        get_government.id_city = data['id_city']
    if data.get('id_role'):
        get_government.id_role = data['id_role']
    db.session.add(get_government)
    db.session.commit()
    government_schema = GovernmentSchema(only=['id', 'name', 'middle_name', 'last_name', 'address', 'date', 'politic_part', 'source', 'id_city', 'id_role'])
    government = government_schema.dump(get_government)

    return make_response(jsonify({"government": government}))


@views.route('/government/<id>', methods=['DELETE'])
def delete_government_by_id(id):
    get_government = Government.query.get(id)
    db.session.delete(get_government)
    db.session.commit()
    return make_response("", 204)
