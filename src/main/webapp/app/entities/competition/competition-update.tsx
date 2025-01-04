import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getSponsors } from 'app/entities/sponsor/sponsor.reducer';
import { CompetitionPaymentStatus } from 'app/shared/model/enumerations/competition-payment-status.model';
import { createEntity, getEntity, reset, updateEntity } from './competition.reducer';

export const CompetitionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const sponsors = useAppSelector(state => state.sponsor.entities);
  const competitionEntity = useAppSelector(state => state.competition.entity);
  const loading = useAppSelector(state => state.competition.loading);
  const updating = useAppSelector(state => state.competition.updating);
  const updateSuccess = useAppSelector(state => state.competition.updateSuccess);
  const competitionPaymentStatusValues = Object.keys(CompetitionPaymentStatus);

  const handleClose = () => {
    navigate('/competition');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getSponsors({}));
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
    if (values.totalPrizeValue !== undefined && typeof values.totalPrizeValue !== 'number') {
      values.totalPrizeValue = Number(values.totalPrizeValue);
    }

    const entity = {
      ...competitionEntity,
      ...values,
      sponsor: sponsors.find(it => it.id.toString() === values.sponsor?.toString()),
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
          status: 'PaymentPendingFromSponsor',
          ...competitionEntity,
          sponsor: competitionEntity?.sponsor?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.competition.home.createOrEditLabel" data-cy="CompetitionCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.competition.home.createOrEditLabel">Create or edit a Competition</Translate>
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
                  id="competition-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.title')}
                id="competition-title"
                name="title"
                data-cy="title"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.description')}
                id="competition-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.status')}
                id="competition-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {competitionPaymentStatusValues.map(competitionPaymentStatus => (
                  <option value={competitionPaymentStatus} key={competitionPaymentStatus}>
                    {translate(`affliateMarketplaceApp.CompetitionPaymentStatus.${competitionPaymentStatus}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.isBlocked')}
                id="competition-isBlocked"
                name="isBlocked"
                data-cy="isBlocked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.blockReason')}
                id="competition-blockReason"
                name="blockReason"
                data-cy="blockReason"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.blockedBy')}
                id="competition-blockedBy"
                name="blockedBy"
                data-cy="blockedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.isPaused')}
                id="competition-isPaused"
                name="isPaused"
                data-cy="isPaused"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.pauseReason')}
                id="competition-pauseReason"
                name="pauseReason"
                data-cy="pauseReason"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.pausedBy')}
                id="competition-pausedBy"
                name="pausedBy"
                data-cy="pausedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.banner1Url')}
                id="competition-banner1Url"
                name="banner1Url"
                data-cy="banner1Url"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.banner2Url')}
                id="competition-banner2Url"
                name="banner2Url"
                data-cy="banner2Url"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.banner3Url')}
                id="competition-banner3Url"
                name="banner3Url"
                data-cy="banner3Url"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.startDate')}
                id="competition-startDate"
                name="startDate"
                data-cy="startDate"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.endDate')}
                id="competition-endDate"
                name="endDate"
                data-cy="endDate"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.landingUrl')}
                id="competition-landingUrl"
                name="landingUrl"
                data-cy="landingUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.totalPrizeValue')}
                id="competition-totalPrizeValue"
                name="totalPrizeValue"
                data-cy="totalPrizeValue"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.invoiceToSponsorUrl')}
                id="competition-invoiceToSponsorUrl"
                name="invoiceToSponsorUrl"
                data-cy="invoiceToSponsorUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.tncUrl')}
                id="competition-tncUrl"
                name="tncUrl"
                data-cy="tncUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.tnc')}
                id="competition-tnc"
                name="tnc"
                data-cy="tnc"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.isActive')}
                id="competition-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.createdBy')}
                id="competition-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.createdOn')}
                id="competition-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.updatedBy')}
                id="competition-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competition.updatedOn')}
                id="competition-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="competition-sponsor"
                name="sponsor"
                data-cy="sponsor"
                label={translate('affliateMarketplaceApp.competition.sponsor')}
                type="select"
              >
                <option value="" key="0" />
                {sponsors
                  ? sponsors.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/competition" replace color="info">
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

export default CompetitionUpdate;
