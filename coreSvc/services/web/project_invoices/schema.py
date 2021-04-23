from marshmallow import fields
from marshmallow_sqlalchemy import ModelSchema
from .models import Legal_forms, Cities, Contractor, Invoices, Evaluated, Roles, Company_management, Government
from project_invoices import db


class Legal_formsSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Legal_forms
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    name = fields.String(required=True)
    short = fields.String(required=True)


class CitiesSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Cities
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    name = fields.String(required=True)


class ContractorSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Contractor
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    name = fields.String(required=True)
    address = fields.String(required=True)
    description = fields.String(required=True)
    date_created = fields.Date(required=True)
    source = fields.String(required=True)
    ico = fields.String(required=True)
    id_leg_form = fields.Integer(required=True)
    id_city = fields.Integer(required=True)


class InvoicesSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Invoices
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    subject = fields.String(required=True)
    description = fields.String(required=True)
    price = fields.Integer(required=True)
    date1 = fields.Date(required=True)
    date2 = fields.Date(required=True)
    date3 = fields.Date(required=True)
    source = fields.String(required=True)
    id_contractor = fields.Integer(required=True)
    id_city = fields.Integer(required=True)



class EvaluatedSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Evaluated
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    value = fields.String(required=True)
    description = fields.String(required=True)
    evaluation = fields.String(required=True)
    id_invoice = fields.Integer(required=True)


class RolesSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Roles
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    role_name = fields.String(required=True)


class Company_managementSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Company_management
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    name = fields.String(required=True)
    middle_name = fields.String(required=True)
    last_name = fields.String(required=True)
    address = fields.String(required=True)
    date_start = fields.Date(required=True)
    source = fields.String(required=True)
    id_city = fields.Integer(required=True)
    id_role = fields.Integer(required=True)
    id_contractor = fields.Integer(required=True)


class GovernmentSchema(ModelSchema):
    class Meta(ModelSchema.Meta):
        model = Government
        sqla_session = db.session
    id  = fields.Number(dump_only=True)
    name = fields.String(required=True)
    middle_name = fields.String(required=True)
    last_name = fields.String(required=True)
    address = fields.String(required=True)
    date = fields.Date(required=True)
    politic_part = fields.String(required=True)
    source = fields.String(required=True)
    id_city = fields.Integer(required=True)
    id_role = fields.Integer(required=True)
