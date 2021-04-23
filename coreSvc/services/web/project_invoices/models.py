from flask_sqlalchemy import SQLAlchemy
# from sqlalchemy.sql import func
from project_invoices import db


class Legal_forms(db.Model):
    __tablename__ = "legal_forms"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255), nullable=False)
    short = db.Column(db.String(255), nullable=False)
    contractor = db.relationship("Contractor", backref=db.backref("legal_forms", uselist=False))

    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

    def __init__(self, name, short):
        self.name = name
        self.short = short

    def __repr__(self):
        return f"{self.id}"


class Cities(db.Model):
    __tablename__ = "cities"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255),unique=True, nullable=False)
    contractors = db.relationship("Contractor", backref=db.backref("cities"))
    invoices = db.relationship("Invoices", backref=db.backref("cities"))
    comp_manag = db.relationship("Company_management", backref=db.backref("cities"))
    government = db.relationship("Government", backref=db.backref("cities"))

    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

    def __init__(self, name):
        self.name = name

    def __repr__(self):
        return f"{self.id}"


class Contractor(db.Model):
    __tablename__ = "contractor"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255),unique=True, nullable=False)
    address = db.Column(db.String(255), nullable=False)
    description = db.Column(db.String(), nullable=False)
    # date_created = db.Column(db.DateTime(timezone=True), server_default=func.now())
    date_created = db.Column(db.Date(), nullable=False)
    source = db.Column(db.String(255), nullable=False)
    ico = db.Column(db.String(255), nullable=True)
    id_leg_form = db.Column(db.Integer, db.ForeignKey('legal_forms.id'))
    id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
    invoices = db.relationship("Invoices", backref=db.backref("contractor"))
    management = db.relationship("Company_management", backref=db.backref("contractor"))
    

    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

    def __init__(self, name, address, description, date_created, source, ico, id_leg_form, id_city):
        self.name = name
        self.address = address
        self.description = description
        self.date_created = date_created
        self.source = source
        self.ico = ico
        self.id_leg_form = id_leg_form
        self.id_city = id_city


    def __repr__(self):
        return f"{self.id}"


class Invoices(db.Model):
    __tablename__ = "invoices"

    id = db.Column(db.Integer, primary_key=True)
    subject = db.Column(db.String(255), nullable=False)
    description = db.Column(db.String(), nullable=False)
    price = db.Column(db.Integer, nullable=False)
    date1 = db.Column(db.Date(), nullable=False)
    date2 = db.Column(db.Date(), nullable=False)
    date3 = db.Column(db.Date(), nullable=False)
    source = db.Column(db.String(255), nullable=False)
    id_contractor = db.Column(db.Integer, db.ForeignKey('contractor.id'))
    id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
    evaluated = db.relationship("Evaluated", backref=db.backref("invoices", uselist=False))

    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

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
    
    def __repr__(self):
        return f"{self.id}"


class Evaluated(db.Model):
    __tablename__ = "evaluated"

    id = db.Column(db.Integer, primary_key=True)
    value = db.Column(db.String(255), nullable=False)
    description = db.Column(db.String(), nullable=False)
    evaluation = db.Column(db.String(255), nullable=True)
    id_invoice = db.Column(db.Integer, db.ForeignKey('invoices.id'))

    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

    def __init__(self, value, description, evaluation, id_invoice):
        self.value = value
        self.description = description
        self.evaluation = evaluation
        self.id_invoice = id_invoice

    def __repr__(self):
        return f"{self.id}"


class Roles(db.Model):
    __tablename__ = "roles"

    id = db.Column(db.Integer, primary_key=True)
    role_name = db.Column(db.String(255), nullable=False)
    comp_manag = db.relationship("Company_management", backref=db.backref("roles"))
    governement = db.relationship("Government", backref=db.backref("roles"))

    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

    def __init__(self, role_name):
        self.role_name = role_name

    def __repr__(self):
        return f"{self.id}"


class Company_management(db.Model):
    __tablename__ = "company_management"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(50), nullable=False)
    middle_name = db.Column(db.String(100), nullable=True)
    last_name = db.Column(db.String(50), nullable=False)
    address = db.Column(db.String(255), nullable=True)
    date_start = db.Column(db.Date(), nullable=False)
    source = db.Column(db.String(255), nullable=False)
    id_city = db.Column(db.Integer, db.ForeignKey('cities.id'))
    id_role = db.Column(db.Integer, db.ForeignKey('roles.id'))
    id_contractor = db.Column(db.Integer, db.ForeignKey('contractor.id'))


    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

    def __init__(self, name, middle_name, last_name, address, date_start, source, id_city, id_role, id_contractor):
        self.name = name
        self.middle_name = middle_name
        self.last_name = last_name
        self.address = address
        self.date_start = date_start
        self.source = source
        self.id_city = id_city
        self.id_role = id_role
        self.id_contractor = id_contractor


    def __repr__(self):
        return f"{self.id}"


class Government(db.Model):
    __tablename__ = "government"

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

    def create(self):
        db.session.add(self)
        db.session.commit()
        return self

    def __init__(self, name, middle_name, last_name, address, date, politic_part, source, id_city, id_role):
        self.name = name
        self.middle_name = middle_name
        self.last_name = last_name
        self.address = address
        self.date = date
        self.politic_part = politic_part
        self.source = source
        self.id_city = id_city
        self.id_role = id_role

    def __repr__(self):
        return f"{self.id}"


db.create_all()
