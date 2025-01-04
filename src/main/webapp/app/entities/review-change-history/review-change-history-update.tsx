import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getReviews } from 'app/entities/review/review.reducer';
import { ChangeType } from 'app/shared/model/enumerations/change-type.model';
import { createEntity, getEntity, reset, updateEntity } from './review-change-history.reducer';

export const ReviewChangeHistoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const reviews = useAppSelector(state => state.review.entities);
  const reviewChangeHistoryEntity = useAppSelector(state => state.reviewChangeHistory.entity);
  const loading = useAppSelector(state => state.reviewChangeHistory.loading);
  const updating = useAppSelector(state => state.reviewChangeHistory.updating);
  const updateSuccess = useAppSelector(state => state.reviewChangeHistory.updateSuccess);
  const changeTypeValues = Object.keys(ChangeType);

  const handleClose = () => {
    navigate('/review-change-history');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getReviews({}));
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
    if (values.oldRating !== undefined && typeof values.oldRating !== 'number') {
      values.oldRating = Number(values.oldRating);
    }

    const entity = {
      ...reviewChangeHistoryEntity,
      ...values,
      review: reviews.find(it => it.id.toString() === values.review?.toString()),
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
          changeType: 'ModeratorUpdateContent',
          ...reviewChangeHistoryEntity,
          review: reviewChangeHistoryEntity?.review?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.reviewChangeHistory.home.createOrEditLabel" data-cy="ReviewChangeHistoryCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.reviewChangeHistory.home.createOrEditLabel">
              Create or edit a ReviewChangeHistory
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
                  id="review-change-history-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.changeType')}
                id="review-change-history-changeType"
                name="changeType"
                data-cy="changeType"
                type="select"
              >
                {changeTypeValues.map(changeType => (
                  <option value={changeType} key={changeType}>
                    {translate(`affliateMarketplaceApp.ChangeType.${changeType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsLiked')}
                id="review-change-history-oldIsLiked"
                name="oldIsLiked"
                data-cy="oldIsLiked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsSkipped')}
                id="review-change-history-oldIsSkipped"
                name="oldIsSkipped"
                data-cy="oldIsSkipped"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsDisliked')}
                id="review-change-history-oldIsDisliked"
                name="oldIsDisliked"
                data-cy="oldIsDisliked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsWatched')}
                id="review-change-history-oldIsWatched"
                name="oldIsWatched"
                data-cy="oldIsWatched"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsFullyWatched')}
                id="review-change-history-oldIsFullyWatched"
                name="oldIsFullyWatched"
                data-cy="oldIsFullyWatched"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsReported')}
                id="review-change-history-oldIsReported"
                name="oldIsReported"
                data-cy="oldIsReported"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldRating')}
                id="review-change-history-oldRating"
                name="oldRating"
                data-cy="oldRating"
                type="text"
                validate={{
                  min: { value: 1, message: translate('entity.validation.min', { min: 1 }) },
                  max: { value: 5, message: translate('entity.validation.max', { max: 5 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldComment')}
                id="review-change-history-oldComment"
                name="oldComment"
                data-cy="oldComment"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldReportReason')}
                id="review-change-history-oldReportReason"
                name="oldReportReason"
                data-cy="oldReportReason"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsBlocked')}
                id="review-change-history-oldIsBlocked"
                name="oldIsBlocked"
                data-cy="oldIsBlocked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsModerated')}
                id="review-change-history-oldIsModerated"
                name="oldIsModerated"
                data-cy="oldIsModerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldIsActive')}
                id="review-change-history-oldIsActive"
                name="oldIsActive"
                data-cy="oldIsActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldCreatedBy')}
                id="review-change-history-oldCreatedBy"
                name="oldCreatedBy"
                data-cy="oldCreatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldCreatedOn')}
                id="review-change-history-oldCreatedOn"
                name="oldCreatedOn"
                data-cy="oldCreatedOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldUpdatedBy')}
                id="review-change-history-oldUpdatedBy"
                name="oldUpdatedBy"
                data-cy="oldUpdatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.reviewChangeHistory.oldUpdatedOn')}
                id="review-change-history-oldUpdatedOn"
                name="oldUpdatedOn"
                data-cy="oldUpdatedOn"
                type="date"
              />
              <ValidatedField
                id="review-change-history-review"
                name="review"
                data-cy="review"
                label={translate('affliateMarketplaceApp.reviewChangeHistory.review')}
                type="select"
              >
                <option value="" key="0" />
                {reviews
                  ? reviews.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/review-change-history" replace color="info">
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

export default ReviewChangeHistoryUpdate;
