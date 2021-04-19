from flask.cli import FlaskGroup

from project_invoices import app, db, Cities


cli = FlaskGroup(app)

@cli.command("create_db")
def create_db():
    db.drop_all()
    db.create_all()
    db.session.commit()

# @cli.command("seed_db")
# def seed_db():
#     db.session.add(Cities(name="Trnava"))
#     db.session.commit()

if __name__ == "__main__":
    cli()