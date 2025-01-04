import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getCompetitions } from 'app/entities/competition/competition.reducer';
import { TransactionStatus } from 'app/shared/model/enumerations/transaction-status.model';
import { createEntity, getEntity, reset, updateEntity } from './competition-payment-from-sponsor.reducer';

export const CompetitionPaymentFromSponsorUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const competitions = useAppSelector(state => state.competition.entities);
  const competitionPaymentFromSponsorEntity = useAppSelector(state => state.competitionPaymentFromSponsor.entity);
  const loading = useAppSelector(state => state.competitionPaymentFromSponsor.loading);
  const updating = useAppSelector(state => state.competitionPaymentFromSponsor.updating);
  const updateSuccess = useAppSelector(state => state.competitionPaymentFromSponsor.updateSuccess);
  const transactionStatusValues = Object.keys(TransactionStatus);

  const handleClose = () => {
    navigate('/competition-payment-from-sponsor');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCompetitions({}));
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
      ...competitionPaymentFromSponsorEntity,
      ...values,
      competition: competitions.find(it => it.id.toString() === values.competition?.toString()),
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
          transactionStatus: 'Initiated',
          ...competitionPaymentFromSponsorEntity,
          competition: competitionPaymentFromSponsorEntity?.competition?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="affliateMarketplaceApp.competitionPaymentFromSponsor.home.createOrEditLabel"
            data-cy="CompetitionPaymentFromSponsorCreateUpdateHeading"
          >
            <Translate contentKey="affliateMarketplaceApp.competitionPaymentFromSponsor.home.createOrEditLabel">
              Create or edit a CompetitionPaymentFromSponsor
            </Translate>
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
                  id="competition-payment-from-sponsor-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.amount')}
                id="competition-payment-from-sponsor-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.transactionId')}
                id="competition-payment-from-sponsor-transactionId"
                name="transactionId"
                data-cy="transactionId"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.transactionScreenshotUrl')}
                id="competition-payment-from-sponsor-transactionScreenshotUrl"
                name="transactionScreenshotUrl"
                data-cy="transactionScreenshotUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.transactionDate')}
                id="competition-payment-from-sponsor-transactionDate"
                name="transactionDate"
                data-cy="transactionDate"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.transactionStatus')}
                id="competition-payment-from-sponsor-transactionStatus"
                name="transactionStatus"
                data-cy="transactionStatus"
                type="select"
              >
                {transactionStatusValues.map(transactionStatus => (
                  <option value={transactionStatus} key={transactionStatus}>
                    {translate(`affliateMarketplaceApp.TransactionStatus.${transactionStatus}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.paidBy')}
                id="competition-payment-from-sponsor-paidBy"
                name="paidBy"
                data-cy="paidBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.paymentReceiptUrl')}
                id="competition-payment-from-sponsor-paymentReceiptUrl"
                name="paymentReceiptUrl"
                data-cy="paymentReceiptUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.isActive')}
                id="competition-payment-from-sponsor-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.createdBy')}
                id="competition-payment-from-sponsor-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.createdOn')}
                id="competition-payment-from-sponsor-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.updatedBy')}
                id="competition-payment-from-sponsor-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.updatedOn')}
                id="competition-payment-from-sponsor-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="competition-payment-from-sponsor-competition"
                name="competition"
                data-cy="competition"
                label={translate('affliateMarketplaceApp.competitionPaymentFromSponsor.competition')}
                type="select"
              >
                <option value="" key="0" />
                {competitions
                  ? competitions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/competition-payment-from-sponsor"
                replace
                color="info"
              >
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

export default CompetitionPaymentFromSponsorUpdate;
