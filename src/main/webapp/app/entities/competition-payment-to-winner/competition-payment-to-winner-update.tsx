import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { TransactionStatus } from 'app/shared/model/enumerations/transaction-status.model';
import { createEntity, getEntity, reset, updateEntity } from './competition-payment-to-winner.reducer';

export const CompetitionPaymentToWinnerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const competitionPaymentToWinnerEntity = useAppSelector(state => state.competitionPaymentToWinner.entity);
  const loading = useAppSelector(state => state.competitionPaymentToWinner.loading);
  const updating = useAppSelector(state => state.competitionPaymentToWinner.updating);
  const updateSuccess = useAppSelector(state => state.competitionPaymentToWinner.updateSuccess);
  const transactionStatusValues = Object.keys(TransactionStatus);

  const handleClose = () => {
    navigate('/competition-payment-to-winner');
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
    if (values.prizeAmount !== undefined && typeof values.prizeAmount !== 'number') {
      values.prizeAmount = Number(values.prizeAmount);
    }
    if (values.tdsAmount !== undefined && typeof values.tdsAmount !== 'number') {
      values.tdsAmount = Number(values.tdsAmount);
    }
    if (values.tdsCertificateUrl !== undefined && typeof values.tdsCertificateUrl !== 'number') {
      values.tdsCertificateUrl = Number(values.tdsCertificateUrl);
    }
    if (values.otherDeductionAmount !== undefined && typeof values.otherDeductionAmount !== 'number') {
      values.otherDeductionAmount = Number(values.otherDeductionAmount);
    }
    if (values.deductionCertificateUrl !== undefined && typeof values.deductionCertificateUrl !== 'number') {
      values.deductionCertificateUrl = Number(values.deductionCertificateUrl);
    }
    if (values.paidAmount !== undefined && typeof values.paidAmount !== 'number') {
      values.paidAmount = Number(values.paidAmount);
    }

    const entity = {
      ...competitionPaymentToWinnerEntity,
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
          transactionStatus: 'Initiated',
          ...competitionPaymentToWinnerEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="affliateMarketplaceApp.competitionPaymentToWinner.home.createOrEditLabel"
            data-cy="CompetitionPaymentToWinnerCreateUpdateHeading"
          >
            <Translate contentKey="affliateMarketplaceApp.competitionPaymentToWinner.home.createOrEditLabel">
              Create or edit a CompetitionPaymentToWinner
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
                  id="competition-payment-to-winner-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.prizeTitle')}
                id="competition-payment-to-winner-prizeTitle"
                name="prizeTitle"
                data-cy="prizeTitle"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.prizeAmount')}
                id="competition-payment-to-winner-prizeAmount"
                name="prizeAmount"
                data-cy="prizeAmount"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.tdsAmount')}
                id="competition-payment-to-winner-tdsAmount"
                name="tdsAmount"
                data-cy="tdsAmount"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.tdsCertificateUrl')}
                id="competition-payment-to-winner-tdsCertificateUrl"
                name="tdsCertificateUrl"
                data-cy="tdsCertificateUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.otherDeductionAmount')}
                id="competition-payment-to-winner-otherDeductionAmount"
                name="otherDeductionAmount"
                data-cy="otherDeductionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.deductionReason')}
                id="competition-payment-to-winner-deductionReason"
                name="deductionReason"
                data-cy="deductionReason"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.deductionJsonData')}
                id="competition-payment-to-winner-deductionJsonData"
                name="deductionJsonData"
                data-cy="deductionJsonData"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.deductionCertificateUrl')}
                id="competition-payment-to-winner-deductionCertificateUrl"
                name="deductionCertificateUrl"
                data-cy="deductionCertificateUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.paidAmount')}
                id="competition-payment-to-winner-paidAmount"
                name="paidAmount"
                data-cy="paidAmount"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.transactionId')}
                id="competition-payment-to-winner-transactionId"
                name="transactionId"
                data-cy="transactionId"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.transactionScreenshotUrl')}
                id="competition-payment-to-winner-transactionScreenshotUrl"
                name="transactionScreenshotUrl"
                data-cy="transactionScreenshotUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.transactionDate')}
                id="competition-payment-to-winner-transactionDate"
                name="transactionDate"
                data-cy="transactionDate"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.transactionStatus')}
                id="competition-payment-to-winner-transactionStatus"
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
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.paidBy')}
                id="competition-payment-to-winner-paidBy"
                name="paidBy"
                data-cy="paidBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.isActive')}
                id="competition-payment-to-winner-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.createdBy')}
                id="competition-payment-to-winner-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.createdOn')}
                id="competition-payment-to-winner-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.updatedBy')}
                id="competition-payment-to-winner-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionPaymentToWinner.updatedOn')}
                id="competition-payment-to-winner-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/competition-payment-to-winner"
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

export default CompetitionPaymentToWinnerUpdate;
