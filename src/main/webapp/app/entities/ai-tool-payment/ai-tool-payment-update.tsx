import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getAiToolSessions } from 'app/entities/ai-tool-session/ai-tool-session.reducer';
import { PaymentStatus } from 'app/shared/model/enumerations/payment-status.model';
import { PgType } from 'app/shared/model/enumerations/pg-type.model';
import { createEntity, getEntity, reset, updateEntity } from './ai-tool-payment.reducer';

export const AiToolPaymentUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const aiToolSessions = useAppSelector(state => state.aiToolSession.entities);
  const aiToolPaymentEntity = useAppSelector(state => state.aiToolPayment.entity);
  const loading = useAppSelector(state => state.aiToolPayment.loading);
  const updating = useAppSelector(state => state.aiToolPayment.updating);
  const updateSuccess = useAppSelector(state => state.aiToolPayment.updateSuccess);
  const paymentStatusValues = Object.keys(PaymentStatus);
  const pgTypeValues = Object.keys(PgType);

  const handleClose = () => {
    navigate('/ai-tool-payment');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAiToolSessions({}));
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
    if (values.amount !== undefined && typeof values.amount !== 'number') {
      values.amount = Number(values.amount);
    }

    const entity = {
      ...aiToolPaymentEntity,
      ...values,
      session: aiToolSessions.find(it => it.id.toString() === values.session?.toString()),
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
          status: 'Initiate',
          pgType: 'RazorPay',
          ...aiToolPaymentEntity,
          session: aiToolPaymentEntity?.session?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.aiToolPayment.home.createOrEditLabel" data-cy="AiToolPaymentCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.aiToolPayment.home.createOrEditLabel">Create or edit a AiToolPayment</Translate>
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
                  id="ai-tool-payment-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.amount')}
                id="ai-tool-payment-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.status')}
                id="ai-tool-payment-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {paymentStatusValues.map(paymentStatus => (
                  <option value={paymentStatus} key={paymentStatus}>
                    {translate(`affliateMarketplaceApp.PaymentStatus.${paymentStatus}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.paymentLinkUrl')}
                id="ai-tool-payment-paymentLinkUrl"
                name="paymentLinkUrl"
                data-cy="paymentLinkUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.pgType')}
                id="ai-tool-payment-pgType"
                name="pgType"
                data-cy="pgType"
                type="select"
              >
                {pgTypeValues.map(pgType => (
                  <option value={pgType} key={pgType}>
                    {translate(`affliateMarketplaceApp.PgType.${pgType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.pgId')}
                id="ai-tool-payment-pgId"
                name="pgId"
                data-cy="pgId"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.pgStatus')}
                id="ai-tool-payment-pgStatus"
                name="pgStatus"
                data-cy="pgStatus"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.pgRequestJson')}
                id="ai-tool-payment-pgRequestJson"
                name="pgRequestJson"
                data-cy="pgRequestJson"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.pgResponseJson')}
                id="ai-tool-payment-pgResponseJson"
                name="pgResponseJson"
                data-cy="pgResponseJson"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.pgRequestTimeStamp')}
                id="ai-tool-payment-pgRequestTimeStamp"
                name="pgRequestTimeStamp"
                data-cy="pgRequestTimeStamp"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.pgResponseTimeStamp')}
                id="ai-tool-payment-pgResponseTimeStamp"
                name="pgResponseTimeStamp"
                data-cy="pgResponseTimeStamp"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.isActive')}
                id="ai-tool-payment-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.createdBy')}
                id="ai-tool-payment-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.createdOn')}
                id="ai-tool-payment-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.updatedBy')}
                id="ai-tool-payment-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.aiToolPayment.updatedOn')}
                id="ai-tool-payment-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="ai-tool-payment-session"
                name="session"
                data-cy="session"
                label={translate('affliateMarketplaceApp.aiToolPayment.session')}
                type="select"
              >
                <option value="" key="0" />
                {aiToolSessions
                  ? aiToolSessions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/ai-tool-payment" replace color="info">
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

export default AiToolPaymentUpdate;
