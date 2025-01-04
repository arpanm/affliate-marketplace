import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getVideoPosts } from 'app/entities/video-post/video-post.reducer';
import { getEntities as getCompetitionPaymentToWinners } from 'app/entities/competition-payment-to-winner/competition-payment-to-winner.reducer';
import { getEntities as getPrizes } from 'app/entities/prize/prize.reducer';
import { createEntity, getEntity, reset, updateEntity } from './competition-winner.reducer';

export const CompetitionWinnerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoPosts = useAppSelector(state => state.videoPost.entities);
  const competitionPaymentToWinners = useAppSelector(state => state.competitionPaymentToWinner.entities);
  const prizes = useAppSelector(state => state.prize.entities);
  const competitionWinnerEntity = useAppSelector(state => state.competitionWinner.entity);
  const loading = useAppSelector(state => state.competitionWinner.loading);
  const updating = useAppSelector(state => state.competitionWinner.updating);
  const updateSuccess = useAppSelector(state => state.competitionWinner.updateSuccess);

  const handleClose = () => {
    navigate('/competition-winner');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getVideoPosts({}));
    dispatch(getCompetitionPaymentToWinners({}));
    dispatch(getPrizes({}));
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
      ...competitionWinnerEntity,
      ...values,
      winningPost: videoPosts.find(it => it.id.toString() === values.winningPost?.toString()),
      paymentToWinner: competitionPaymentToWinners.find(it => it.id.toString() === values.paymentToWinner?.toString()),
      competitionPrize: prizes.find(it => it.id.toString() === values.competitionPrize?.toString()),
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
          ...competitionWinnerEntity,
          winningPost: competitionWinnerEntity?.winningPost?.id,
          paymentToWinner: competitionWinnerEntity?.paymentToWinner?.id,
          competitionPrize: competitionWinnerEntity?.competitionPrize?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.competitionWinner.home.createOrEditLabel" data-cy="CompetitionWinnerCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.competitionWinner.home.createOrEditLabel">
              Create or edit a CompetitionWinner
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
                  id="competition-winner-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.prizeTitle')}
                id="competition-winner-prizeTitle"
                name="prizeTitle"
                data-cy="prizeTitle"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.citation')}
                id="competition-winner-citation"
                name="citation"
                data-cy="citation"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.certificateUrl')}
                id="competition-winner-certificateUrl"
                name="certificateUrl"
                data-cy="certificateUrl"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.selectedBy')}
                id="competition-winner-selectedBy"
                name="selectedBy"
                data-cy="selectedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.selectionReason')}
                id="competition-winner-selectionReason"
                name="selectionReason"
                data-cy="selectionReason"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.isActive')}
                id="competition-winner-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.createdBy')}
                id="competition-winner-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.createdOn')}
                id="competition-winner-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.updatedBy')}
                id="competition-winner-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.competitionWinner.updatedOn')}
                id="competition-winner-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="competition-winner-winningPost"
                name="winningPost"
                data-cy="winningPost"
                label={translate('affliateMarketplaceApp.competitionWinner.winningPost')}
                type="select"
              >
                <option value="" key="0" />
                {videoPosts
                  ? videoPosts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="competition-winner-paymentToWinner"
                name="paymentToWinner"
                data-cy="paymentToWinner"
                label={translate('affliateMarketplaceApp.competitionWinner.paymentToWinner')}
                type="select"
              >
                <option value="" key="0" />
                {competitionPaymentToWinners
                  ? competitionPaymentToWinners.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="competition-winner-competitionPrize"
                name="competitionPrize"
                data-cy="competitionPrize"
                label={translate('affliateMarketplaceApp.competitionWinner.competitionPrize')}
                type="select"
              >
                <option value="" key="0" />
                {prizes
                  ? prizes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/competition-winner" replace color="info">
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

export default CompetitionWinnerUpdate;
