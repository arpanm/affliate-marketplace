import dayjs from 'dayjs';
import { IReview } from 'app/shared/model/review.model';
import { ChangeType } from 'app/shared/model/enumerations/change-type.model';

export interface IReviewChangeHistory {
  id?: number;
  changeType?: keyof typeof ChangeType | null;
  oldIsLiked?: boolean | null;
  oldIsSkipped?: boolean | null;
  oldIsDisliked?: boolean | null;
  oldIsWatched?: boolean | null;
  oldIsFullyWatched?: boolean | null;
  oldIsReported?: boolean | null;
  oldRating?: number | null;
  oldComment?: string | null;
  oldReportReason?: string | null;
  oldIsBlocked?: boolean | null;
  oldIsModerated?: boolean | null;
  oldIsActive?: boolean | null;
  oldCreatedBy?: string | null;
  oldCreatedOn?: dayjs.Dayjs | null;
  oldUpdatedBy?: string | null;
  oldUpdatedOn?: dayjs.Dayjs | null;
  review?: IReview | null;
}

export const defaultValue: Readonly<IReviewChangeHistory> = {
  oldIsLiked: false,
  oldIsSkipped: false,
  oldIsDisliked: false,
  oldIsWatched: false,
  oldIsFullyWatched: false,
  oldIsReported: false,
  oldIsBlocked: false,
  oldIsModerated: false,
  oldIsActive: false,
};
