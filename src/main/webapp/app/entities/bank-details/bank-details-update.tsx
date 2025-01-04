import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './bank-details.reducer';

export const BankDetailsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const bankDetailsEntity = useAppSelector(state => state.bankDetails.entity);
  const loading = useAppSelector(state => state.bankDetails.loading);
  const updating = useAppSelector(state => state.bankDetails.updating);
  const updateSuccess = useAppSelector(state => state.bankDetails.updateSuccess);

  const handleClose = () => {
    navigate('/bank-details');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...bankDetailsEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...bankDetailsEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.bankDetails.home.createOrEditLabel" data-cy="BankDetailsCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.bankDetails.home.createOrEditLabel">Create or edit a BankDetails</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="bank-details-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.accountName')}
                id="bank-details-accountName"
                name="accountName"
                data-cy="accountName"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.accountNo')}
                id="bank-details-accountNo"
                name="accountNo"
                data-cy="accountNo"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.bankName')}
                id="bank-details-bankName"
                name="bankName"
                data-cy="bankName"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.ifsc')}
                id="bank-details-ifsc"
                name="ifsc"
                data-cy="ifsc"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.proofUrl')}
                id="bank-details-proofUrl"
                name="proofUrl"
                data-cy="proofUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.isActive')}
                id="bank-details-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.createdBy')}
                id="bank-details-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.createdOn')}
                id="bank-details-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.updatedBy')}
                id="bank-details-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.bankDetails.updatedOn')}
                id="bank-details-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/bank-details" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default BankDetailsUpdate;
